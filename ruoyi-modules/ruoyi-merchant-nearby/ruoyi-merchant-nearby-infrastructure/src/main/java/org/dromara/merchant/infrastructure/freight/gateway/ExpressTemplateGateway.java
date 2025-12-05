package org.dromara.merchant.infrastructure.freight.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.freight.gateway.IExpressTemplateGateway;
import org.dromara.merchant.domain.freight.model.ExpressTemplate;
import org.dromara.merchant.infrastructure.freight.gateway.dataobject.ExpressTemplateDO;
import org.dromara.merchant.infrastructure.freight.converter.ExpressTemplateConvertor;
import org.dromara.merchant.infrastructure.freight.mapper.ExpressTemplateMapper;
import org.springframework.stereotype.Component;

/**
 * @Description 商户运费模板网关实现
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Component
@RequiredArgsConstructor
public class ExpressTemplateGateway implements IExpressTemplateGateway {

    private final ExpressTemplateMapper mapper;
    private final ExpressTemplateConvertor convertor;

    /**
     * 保存商户运费模板
     *
     * @param template 运费模板实体
     * @return 是否保存成功
     */
    public boolean save(ExpressTemplate template) {
        ExpressTemplateDO templateDO = convertor.toMerchantShippingTemplateDO(template);
        return mapper.insertOrUpdate(templateDO);
    }

    /**
     * 根据ID查询商户运费模板
     *
     * @param templateId 模板ID
     * @return 运费模板实体
     */
    public ExpressTemplate findById(Long templateId) {
        ExpressTemplateDO templateDO = mapper.selectById(templateId);
        return convertor.toMerchantShippingTemplateEntity(templateDO);
    }

    /**
     * 删除商户运费模板
     *
     * @param templateId 模板ID
     * @return 是否删除成功
     */
    public boolean deleteById(Long templateId) {
        return mapper.deleteById(templateId) > 0;
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
        return mapper.setDefaultByMerchantId(templateId, merchantId) > 0;
    }

    /**
     * 获取商户默认运费模板
     *
     * @param merchantId 商户ID
     * @return 默认运费模板实体
     */
    @Override
    public ExpressTemplate getDefaultByMerchantId(Long merchantId) {
        ExpressTemplateDO templateDO = mapper.selectByMerchantIdAndIsDefault(merchantId);
        return this.convertor.toMerchantShippingTemplateEntity(templateDO);
    }

}
