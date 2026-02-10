package org.dromara.common.redis.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.dromara.common.core.utils.SpringUtils;
import org.redisson.api.*;
import org.redisson.api.geo.GeoSearchArgs;
import org.redisson.api.geo.OptionalGeoSearch;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Redis GEO 地理位置工具类
 * 用于处理地理位置、距离计算和附近搜索功能
 *
 * @author Lion Li
 * @version 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeoUtils {

    private static final RedissonClient CLIENT = SpringUtils.getBean(RedissonClient.class);

    // 默认地球半径（米）
    private static final double EARTH_RADIUS = 6371000;

    /**
     * 添加地理位置信息
     *
     * @param key       GEO键
     * @param longitude 经度
     * @param latitude  纬度
     * @param member    成员名称
     * @return 添加成功返回true
     */
    public static <T> boolean addLocation(String key, double longitude, double latitude, T member) {
        RGeo<T> geo = CLIENT.getGeo(key);
        return geo.add(new GeoEntry(longitude, latitude, member)) > 0;
    }

    /**
     * 批量添加地理位置信息
     *
     * @param key       GEO键
     * @param locations 位置列表 (longitude, latitude, member)
     * @return 添加成功的数量
     */
    public static <T> long addLocations(String key, List<GeoLocation<T>> locations) {
        if (locations == null || locations.isEmpty()) {
            return 0;
        }

        RGeo<T> geo = CLIENT.getGeo(key);
        long count = 0;
        for (GeoLocation<T> location : locations) {
            long result = geo.add(new GeoEntry(location.getLongitude(), location.getLatitude(), location.getMember()));
            count += result;
        }
        return count;
    }

    /**
     * 获取地理位置信息
     *
     * @param key     GEO键
     * @param members 成员名称数组
     * @return 位置信息列表
     */
    @SafeVarargs
    public static <T> List<GeoPosition> getPosition(String key, T... members) {
        RGeo<T> geo = CLIENT.getGeo(key);
        Map<T, GeoPosition> positionMap = geo.pos(members);
        return positionMap.values().stream()
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    /**
     * 计算两个地理位置之间的距离
     *
     * @param key     GEO键
     * @param member1 成员1
     * @param member2 成员2
     * @param geoUnit 距离单位
     * @return 距离值
     */
    public static Double getDistance(String key, String member1, String member2, GeoUnit geoUnit) {
        RGeo<String> geo = CLIENT.getGeo(key);
        return geo.dist(member1, member2, geoUnit);
    }

    /**
     * 计算两个经纬度之间的距离（使用Haversine公式）
     *
     * @param lon1    经度1
     * @param lat1    纬度1
     * @param lon2    经度2
     * @param lat2    纬度2
     * @param geoUnit 距离单位
     * @return 距离值
     */
    public static double calculateDistance(double lon1, double lat1, double lon2, double lat2, GeoUnit geoUnit) {
        // 将角度转换为弧度
        double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);
        double deltaLatRad = Math.toRadians(lat2 - lat1);
        double deltaLonRad = Math.toRadians(lon2 - lon1);

        // Haversine公式
        double a = Math.sin(deltaLatRad / 2) * Math.sin(deltaLatRad / 2) +
            Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                Math.sin(deltaLonRad / 2) * Math.sin(deltaLonRad / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;

        // 转换单位
        return switch (geoUnit) {
            case KILOMETERS -> distance / 1000.0;
            case MILES -> distance / 1609.344;
            case FEET -> distance / 0.3048;
            default -> distance;
        };
    }

    /**
     * 搜索指定半径范围内的地点
     *
     * @param key       GEO键
     * @param longitude 经度
     * @param latitude  纬度
     * @param radius    半径
     * @param geoUnit   距离单位
     * @return 范围内的成员列表
     */
    public static <T> List<T> searchNearby(String key, double longitude, double latitude, double radius, GeoUnit geoUnit) {
        RGeo<T> geo = CLIENT.getGeo(key);
        OptionalGeoSearch radiused = GeoSearchArgs.from(longitude, latitude).radius(radius, geoUnit);
        return new ArrayList<>(geo.search(radiused));
    }

    /**
     * 搜索指定半径范围内的地点（带距离信息）
     *
     * @param key       GEO键
     * @param longitude 经度
     * @param latitude  纬度
     * @param radius    半径
     * @param geoUnit   距离单位
     * @return 范围内的成员和距离信息
     */
    public static <T> Map<T, Double> searchNearbyWithDistance(String key, double longitude, double latitude,
                                                              double radius, GeoUnit geoUnit) {
        RGeo<T> geo = CLIENT.getGeo(key);
        OptionalGeoSearch radiused = GeoSearchArgs.from(longitude, latitude).radius(radius, geoUnit);
        return geo.searchWithDistance(radiused);
    }

    /**
     * 搜索指定半径范围内的地点（限制返回数量）
     *
     * @param key       GEO键
     * @param longitude 经度
     * @param latitude  纬度
     * @param radius    半径
     * @param geoUnit   距离单位
     * @param count     返回数量限制
     * @return 范围内的成员列表
     */
    public static <T> List<T> searchNearbyLimit(String key, double longitude, double latitude,
                                                double radius, GeoUnit geoUnit, int count) {
        RGeo<T> geo = CLIENT.getGeo(key);
        List<T> result = geo.search(GeoSearchArgs.from(longitude, latitude).radius(radius, geoUnit));
        return result.stream().limit(count).collect(Collectors.toList());
    }

    /**
     * 搜索指定矩形范围内的地点
     *
     * @param key                 GEO键
     * @param longitudeLeftBottom 左下角经度
     * @param latitudeLeftBottom  左下角纬度
     * @param longitudeRightTop   右上角经度
     * @param latitudeRightTop    右上角纬度
     * @return 范围内的成员列表
     */
    public static <T> List<T> searchInBox(String key, double longitudeLeftBottom, double latitudeLeftBottom,
                                          double longitudeRightTop, double latitudeRightTop, GeoUnit geoUnit) {
        // Redisson RGeo 不直接支持 box 查询，使用 radius 近似实现
        double centerLon = (longitudeLeftBottom + longitudeRightTop) / 2;
        double centerLat = (latitudeLeftBottom + latitudeRightTop) / 2;
        // 计算对角线距离的一半作为半径
        double radius = calculateDistance(longitudeLeftBottom, latitudeLeftBottom, longitudeRightTop, latitudeRightTop, geoUnit) / 2;
        return searchNearby(key, centerLon, centerLat, radius, geoUnit);
    }

    /**
     * 获取附近地点按距离排序
     *
     * @param key       GEO键
     * @param longitude 经度
     * @param latitude  纬度
     * @param radius    半径
     * @param geoUnit   距离单位
     * @param count     返回数量
     * @return 按距离排序的附近地点信息
     */
    public static <T> List<NearbyLocation<T>> getNearbySorted(String key, double longitude, double latitude,
                                                              double radius, GeoUnit geoUnit, int count) {
        RGeo<T> geo = CLIENT.getGeo(key);
        OptionalGeoSearch radiused = GeoSearchArgs.from(longitude, latitude).radius(radius, geoUnit);
        List<T> search = geo.search(radiused);

        List<NearbyLocation<T>> locations = new ArrayList<>();
        for (T member : search) {
            Map<T, Double> map = geo.searchWithDistance(radiused);
            if (map.get(member) != null) {
                locations.add(new NearbyLocation<>(member, map.get(member)));
            }
        }

        return locations.stream()
            .sorted(Comparator.comparing(NearbyLocation::getDistance))
            .limit(count)
            .collect(Collectors.toList());
    }

    /**
     * 删除地理位置信息
     *
     * @param key     GEO键
     * @param members 成员名称数组
     * @return 删除成功的数量
     */
    public static <T> long removeLocations(String key, T... members) {
        RGeo<T> geo = CLIENT.getGeo(key);
        long count = 0;
        for (T member : members) {
            boolean removed = geo.remove(member);
            if (removed) {
                count++;
            }
        }
        return count;
    }

    /**
     * 获取指定成员的Hash值
     *
     * @param key     GEO键
     * @param members 成员名称数组
     * @return Hash值列表
     */
    public static <T> List<Long> getHash(String key, T... members) {
        RGeo<T> geo = CLIENT.getGeo(key);
        Map<T, String> hashStrings = geo.hash(members);
        List<Long> hashValues = new ArrayList<>();
        for (String hashStr : hashStrings.values()) {
            try {
                hashValues.add(Long.parseLong(hashStr, 10));
            } catch (NumberFormatException e) {
                // 忽略无法解析的hash值
            }
        }
        return hashValues;
    }

    /**
     * 判断成员是否存在
     *
     * @param key    GEO键
     * @param member 成员名称
     * @return 存在返回true
     */
    public static <T> boolean exists(String key, T member) {
        List<GeoPosition> positions = getPosition(key, member);
        return positions != null && !positions.isEmpty() && positions.get(0) != null;
    }

    /**
     * 获取GEO键中所有成员
     *
     * @param key GEO键
     * @return 所有成员列表
     */
    public static Set<String> getAllMembers(String key) {
        RGeo<String> geo = CLIENT.getGeo(key);
        return new HashSet<>(geo.readAll());
    }

    /**
     * 获取GEO键中成员数量
     *
     * @param key GEO键
     * @return 成员数量
     */
    public static long getSize(String key) {
        RGeo<String> geo = CLIENT.getGeo(key);
        return geo.size();
    }

    /**
     * 清空GEO键
     *
     * @param key GEO键
     * @return 删除成功返回true
     */
    public static boolean clear(String key) {
        return RedisUtils.deleteObject(key);
    }

}
