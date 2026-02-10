package org.dromara.merchant.app.merchant.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.redis.utils.GeoLocation;
import org.dromara.common.redis.utils.GeoUtils;
import org.dromara.merchant.domain.merchant.gateway.IMerchantGateway;
import org.dromara.merchant.domain.merchant.model.Merchant;
import org.redisson.api.GeoUnit;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.hutool.core.text.StrPool.COLON;
import static cn.hutool.core.text.StrPool.COMMA;

/**
 * 商家地理位置缓存服务
 * 用于将商家地理位置信息存储到Redis GEO中，支持附近商家搜索
 *
 * @author Lion Li
 * @version 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MerchantGeoCacheService {

    private final IMerchantGateway merchantGateway;

    // Redis GEO 键名
    public static final String MERCHANT_GEO_KEY = "merchant:nearby:geo";

    /**
     * 预热商家地理位置缓存
     * 将数据库中的所有有效商家地理位置信息加载到Redis GEO中
     */
    public void warmupMerchantGeoCache() {
        log.info("开始预热商家地理位置缓存...");

        // 查询所有有效商家（活跃且已认证，且有地理位置信息）
        List<Merchant> merchantList = merchantGateway.selectAllValidMerchants();

        if (merchantList.isEmpty()) {
            log.info("没有有效商家需要预热");
            return;
        }

        Map<String, List<Long>> merchantMap = new HashMap<>();

        // 解析商家地理位置信息并构建GeoLocation列表
        List<GeoLocation<Long>> geoLocations = new ArrayList<>();

        merchantList.forEach(merchant -> {
            String preciseLocation = merchant.getPreciseLocation();
            if (!StringUtils.hasText(preciseLocation)) return;
            String[] locationParts = preciseLocation.split(COMMA);
            if (locationParts.length != 2) return;

            double longitude = Double.parseDouble(locationParts[0].trim());
            double latitude = Double.parseDouble(locationParts[1].trim());
            if (!isValidCoordinate(longitude, latitude)) return;

            geoLocations.add(new GeoLocation<>(longitude, latitude, merchant.getMerchantId()));
            merchantMap.computeIfAbsent(merchant.getTenantId(), k -> new ArrayList<>()).add(merchant.getMerchantId());
        });

        log.info("准备添加 {} 个商家到Redis GEO缓存", geoLocations.size());


        // 批量添加地理位置信息
        long addedCount = 0;
        for (Map.Entry<String, List<Long>> entry : merchantMap.entrySet()) {
            String tenantId = entry.getKey();
            List<Long> merchantIds = entry.getValue();
            List<GeoLocation<Long>> tenantGeoLocations = geoLocations.stream()
                .filter(location -> merchantIds.contains(location.getMember()))
                .collect(Collectors.toList());

            // 清空现有缓存
            GeoUtils.clear(tenantId + COLON + MERCHANT_GEO_KEY);
            addedCount += GeoUtils.addLocations(tenantId + COLON + MERCHANT_GEO_KEY, tenantGeoLocations);
        }

        log.info("商家地理位置缓存预热完成，共添加 {} 个商家", addedCount);
    }

    /**
     * 验证经纬度是否有效
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @return 是否有效
     */
    private boolean isValidCoordinate(double longitude, double latitude) {
        // 经度范围: -180 到 180
        // 纬度范围: -90 到 90
        return longitude >= -180 && longitude <= 180 && latitude >= -90 && latitude <= 90;
    }

    /**
     * 添加单个商家到地理位置缓存
     *
     * @param merchantId      商家ID
     * @param preciseLocation 精确位置字符串，格式为 "longitude,latitude"
     * @return 添加是否成功
     */
    public boolean addMerchantToGeoCache(Long merchantId, String preciseLocation) {
        if (merchantId == null || !StringUtils.hasText(preciseLocation)) {
            log.warn("商家ID或位置信息为空，无法添加到地理位置缓存");
            return false;
        }

        try {
            String[] locationParts = preciseLocation.split(COMMA);
            if (locationParts.length != 2) return false;
            double longitude = Double.parseDouble(locationParts[0].trim());
            double latitude = Double.parseDouble(locationParts[1].trim());

            if (!isValidCoordinate(longitude, latitude)) return false;

            return GeoUtils.addLocation(MERCHANT_GEO_KEY, longitude, latitude, merchantId);
        } catch (NumberFormatException e) {
            log.warn("商家 {} 的地理位置格式错误: {}，无法添加到缓存", merchantId, preciseLocation);
            return false;
        }
    }

    /**
     * 从地理位置缓存中移除商家
     *
     * @param merchantId 商家ID
     * @return 移除是否成功
     */
    public boolean removeMerchantFromGeoCache(Long merchantId) {
        if (merchantId == null) {
            log.warn("商家ID为空，无法从地理位置缓存中移除");
            return false;
        }

        long removedCount = GeoUtils.removeLocations(MERCHANT_GEO_KEY, merchantId);
        return removedCount > 0;
    }

    /**
     * 根据地理位置搜索附近的商家
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @param radius    搜索半径（米）
     * @return 附近商家ID列表
     */
    public List<Long> searchNearbyMerchants(double longitude, double latitude, double radius) {
        return GeoUtils.searchNearby(MERCHANT_GEO_KEY, longitude, latitude, radius, GeoUnit.KILOMETERS);
    }

    /**
     * 根据地理位置搜索附近的商家（带距离信息）
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @param radius    搜索半径（米）
     * @return 附近商家ID及距离信息映射
     */
    public Map<Long, Double> searchNearbyMerchantsWithDistance(double longitude, double latitude, double radius) {
        return GeoUtils.searchNearbyWithDistance(MERCHANT_GEO_KEY, longitude, latitude, radius, GeoUnit.KILOMETERS);
    }

    /**
     * 获取缓存中的商家总数
     *
     * @return 商家总数
     */
    public long getMerchantCount() {
        return GeoUtils.getSize(MERCHANT_GEO_KEY);
    }
}
