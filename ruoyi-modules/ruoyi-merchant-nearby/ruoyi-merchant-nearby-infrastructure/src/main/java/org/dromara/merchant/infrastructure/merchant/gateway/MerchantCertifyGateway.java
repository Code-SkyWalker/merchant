package org.dromara.merchant.infrastructure.merchant.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.merchant.gateway.IMerchantCertifyGateway;
import org.dromara.merchant.domain.merchant.model.MerchantCertify;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantCertifyConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.MerchantCertifyMapper;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantCertifyDO;
import org.springframework.stereotype.Component;


/**
 * @Description 商户审批网关实现
 * @Author Code Skywalker
 * @Date 2025/12/1 16:30
 */
@Component
@RequiredArgsConstructor
public class MerchantCertifyGateway implements IMerchantCertifyGateway {

    private final MerchantCertifyMapper mapper;
    private final MerchantCertifyConvertor convertor;

    /**
     * 保存商户审批信息
     *
     * @param merchantCertify 商户审批实体
     * @return 是否保存成功
     */
    @Override
    public boolean save(MerchantCertify merchantCertify) {
        MerchantCertifyDO merchantCertifyDO = convertor.toMerchantCertifyDO(merchantCertify);
        return mapper.insertOrUpdate(merchantCertifyDO);
    }

    /**
     * 根据ID查询商户审批信息
     *
     * @param approvalId 商户ID
     * @return 商户审批实体
     */
    @Override
    public MerchantCertify findById(Long approvalId) {
        MerchantCertifyDO merchantCertifyDO = this.mapper.selectById(approvalId);
        return this.convertor.toMerchantCertifyEntity(merchantCertifyDO);
    }

    /**
     * 删除商户审批信息
     *
     * @param approvalId 商户ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteByPrimaryKey(Long approvalId) {
        return mapper.deleteById(approvalId) > 0;
    }

}
