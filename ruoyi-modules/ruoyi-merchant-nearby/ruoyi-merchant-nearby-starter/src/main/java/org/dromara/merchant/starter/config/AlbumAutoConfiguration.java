package org.dromara.merchant.starter.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 相册自动配置类
 * @Author Code Skywalker
 * @Date 2025/12/17 16:35
 */
@Configuration
@ComponentScan(basePackages = {
    "org.dromara.merchant.app.merchant.service",
    "org.dromara.merchant.infrastructure.merchant.converter"
})
@MapperScan("org.dromara.merchant.infrastructure.merchant.mapper")
public class AlbumAutoConfiguration {
}
