package org.dromara.merchant.app.merchant.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.merchant.app.merchant.service.MerchantGeoCacheService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 商家地理位置缓存预热启动器
 * 应用启动时自动预热商家地理位置缓存
 *
 * @author Lion Li
 * @version 1.0.0
 */
@Slf4j
@Component
@Order(100) // 设置优先级，确保在其他组件之后但早于业务组件执行
@RequiredArgsConstructor
public class MerchantGeoWarmupRunner implements CommandLineRunner {

    private final MerchantGeoCacheService merchantGeoCacheService;

    @Override
    public void run(String... args) throws Exception {
        log.info("开始执行商家地理位置缓存预热...");
        
        try {
            // 预热商家地理位置缓存
            merchantGeoCacheService.warmupMerchantGeoCache();
            log.info("商家地理位置缓存预热完成");
        } catch (Exception e) {
            log.error("商家地理位置缓存预热失败", e);
            // 不抛出异常，避免影响应用启动
        }
    }
}