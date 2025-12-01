package org.dromara.merchant.app.merchant.executor.query;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantCO;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.MerchantMapper;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantDO;
import org.springframework.stereotype.Component;

/**
 * @Description 商户详情查询执行器
 * @Author Code Skywalker
 * @Date 2025/12/1 16:00
 */
@Component
@RequiredArgsConstructor
public class MerchantDetailQryExecutor {

    private final MerchantMapper mapper;
    private final MerchantConvertor convertor;

    public MerchantCO execute(Long merchantId) {
        MerchantDO merchant = mapper.selectByPrimaryKey(merchantId);
        return this.convertor.toMerchantCO(merchant);
    }

}
