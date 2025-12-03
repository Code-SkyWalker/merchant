package org.dromara.merchant.infrastructure.merchant.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.merchant.gateway.IMerchantShippingTemplateGateway;
import org.dromara.merchant.domain.merchant.model.MerchantShippingTemplate;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantShippingTemplateConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.MerchantShippingTemplateMapper;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantShippingTemplateDO;
import org.springframework.stereotype.Component;

/**
 * @Description 商户运费模板网关实现
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Component
@RequiredArgsConstructor
public class MerchantShippingTemplateGatewayImpl implements IMerchantShippingTemplateGateway {

    private final MerchantShippingTemplateMapper mapper;
    private final MerchantShippingTemplateConvertor convertor;

    /**
     * 保存商户运费模板
     *
     * @param template 运费模板实体
     * @return 是否保存成功
     */
    public boolean save(MerchantShippingTemplate template) {
        return mapper.insertOrUpdateSelective(convertor.toMerchantShippingTemplateDO(template)) > 0;
    }

    /**
     * 根据ID查询商户运费模板
     *
     * @param templateId 模板ID
     * @return 运费模板实体
     */
    public MerchantShippingTemplate findById(Long templateId) {
        MerchantShippingTemplateDO templateDO = mapper.selectByPrimaryKey(templateId);
        return templateDO == null ? null : convertor.toMerchantShippingTemplateEntity(templateDO);
    }

    /**
     * 删除商户运费模板
     *
     * @param templateId 模板ID
     * @return 是否删除成功
     */
    public boolean deleteById(Long templateId) {
        return mapper.deleteByPrimaryKey(templateId) > 0;
    }

    /**
     * 设置默认运费模板
     *
     * @param templateId 模板ID
     * @param merchantId 商户ID
     * @return 是否设置成功
     */
    @Override
    public boolean setDefault(Long templateId, Long merchantId) {
        // TODO: 需要实现设置默认模板的逻辑
        return true;
    }

    /**
     * 取消商户所有模板的默认状态
     *
     * @param merchantId 商户ID
     * @return 是否取消成功
     */
    @Override
    public boolean cancelDefaultByMerchantId(Long merchantId) {
        // TODO: 需要实现取消默认模板的逻辑
        return true;
    }

    /**
     * 获取商户默认运费模板
     *
     * @param merchantId 商户ID
     * @return 默认运费模板实体
     */
    @Override
    public MerchantShippingTemplate getDefaultByMerchantId(Long merchantId) {
        // TODO: 需要实现获取默认模板的逻辑
        return null;
    }

}