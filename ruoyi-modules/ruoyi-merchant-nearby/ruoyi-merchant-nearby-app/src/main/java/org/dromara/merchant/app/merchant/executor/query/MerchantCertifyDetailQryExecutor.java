package org.dromara.merchant.app.merchant.executor.query;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantCertifyCO;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantCertifyConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.MerchantCertifyMapper;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantCertifyDO;
import org.springframework.stereotype.Component;

/**
 * @Description 商户详情查询执行器
 * @Author Code Skywalker
 * @Date 2025/12/1 16:00
 */
@Component
@RequiredArgsConstructor
public class MerchantCertifyDetailQryExecutor {

    private final MerchantCertifyMapper mapper;
    private final MerchantCertifyConvertor convertor;

    public MerchantCertifyCO execute(Long approvalId) {
        MerchantCertifyDO certifyDO = mapper.selectByPrimaryKey(approvalId);
        return this.convertor.toMerchantCertifyCO(certifyDO);
    }

}
