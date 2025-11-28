package org.dromara.common.mybatis.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import org.dromara.common.mybatis.interceptor.AutoFillInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 *
 * @author your-name
 */
@Configuration
public class MybatisConfig {

    /**
     * 注册自定义自动填充拦截器
     *
     * @return 自动填充拦截器
     */
    @Bean
    public AutoFillInterceptor autoFillInterceptor() {
        return new AutoFillInterceptor();
    }

    /**
     * MyBatis配置定制器
     *
     * @return 配置定制器
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {
            // 添加自定义拦截器
            configuration.addInterceptor(autoFillInterceptor());
        };
    }
}
