package org.dromara.merchant.infrastructure.merchant.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.merchant.gateway.IMerchantGateway;
import org.dromara.merchant.domain.merchant.model.Merchant;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.MerchantMapper;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantDO;
import org.springframework.stereotype.Component;

/**
 * @Description 商户网关实现
 * @Author Code Skywalker
 * @Date 2025/12/1 15:30
 */
@Component
@RequiredArgsConstructor
public class MerchantGateway implements IMerchantGateway {

    private final MerchantMapper mapper;
    private final MerchantConvertor convertor;

    /**
     * 保存商户
     *
     * @param merchant 商户实体
     * @return 是否保存成功
     */
    public boolean save(Merchant merchant) {
        return mapper.insertOrUpdate(convertor.toMerchantDO(merchant));
    }

    /**
     * 根据ID查询商户
     *
     * @param merchantId 商户ID
     * @return 商户实体
     */
    public Merchant findById(Long merchantId) {
        MerchantDO merchantDO = mapper.selectById(merchantId);
        return convertor.toMerchantEntity(merchantDO);
    }

    /**
     * 删除商户
     *
     * @param merchantId 商户ID
     * @return 是否删除成功
     */
    public boolean deleteById(Long merchantId) {
        return mapper.deleteById(merchantId) > 0;
    }

}
