package org.dromara.huifu.infrastructure.config;

import com.huifu.bspay.sdk.opps.core.BasePay;
import com.huifu.bspay.sdk.opps.core.config.MerConfig;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.huifu.domain.gateway.IHuifuConfigGateway;
import org.dromara.huifu.domain.model.HuifuConfig;
import org.dromara.huifu.infrastructure.convertor.HuifuConfigConvertor;
import org.dromara.huifu.infrastructure.mapper.HuifuConfigMapper;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static com.huifu.bspay.sdk.opps.core.BasePay.MODE_PROD;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025-11-07 16:09
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class HuifuMerchantConfig {

    private final IHuifuConfigGateway gateway;
    private final HuifuConfigConvertor convertor;

    @PostConstruct
    public void init() throws Exception {
        HuifuConfig huifuConfig = gateway.queryChannelConfig();
        MerConfig config = convertor.toConfig(huifuConfig);
        if (config != null) BasePay.initWithMerConfig(config);
        log.info("初始化汇付渠道服务商商配置");
    }

}
