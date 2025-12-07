package org.dromara.merchant.infrastructure.freight.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.merchant.client.freight.dto.data.clientobject.ExpressTemplateCO;
import org.dromara.merchant.client.freight.dto.data.command.ExpressTemplateCreateCmd;
import org.dromara.merchant.client.freight.dto.data.command.ExpressTemplateModifyCmd;
import org.dromara.merchant.domain.freight.model.ExpressTemplate;
import org.dromara.merchant.infrastructure.freight.mapper.dataobject.ExpressTemplateDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/**
 * @Description 商户运费模板转换器
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpressTemplateConvertor {

    /**
     * MerchantShippingTemplate实体转MerchantShippingTemplateDO
     *
     * @param template MerchantShippingTemplate实体
     * @return MerchantShippingTemplateDO
     */
    ExpressTemplateDO toMerchantShippingTemplateDO(ExpressTemplate template);

    /**
     * MerchantShippingTemplateDO转MerchantShippingTemplate实体
     *
     * @param templateDO MerchantShippingTemplateDO
     * @return MerchantShippingTemplate实体
     */
    ExpressTemplate toMerchantShippingTemplateEntity(ExpressTemplateDO templateDO);

    /**
     * MerchantShippingTemplateCreateCmd转MerchantShippingTemplate实体
     *
     * @param cmd MerchantShippingTemplateCreateCmd
     * @return MerchantShippingTemplate实体
     */
    ExpressTemplate toMerchantShippingTemplateEntity(ExpressTemplateCreateCmd cmd);

    /**
     * MerchantShippingTemplateModifyCmd转MerchantShippingTemplate实体
     *
     * @param cmd MerchantShippingTemplateModifyCmd
     * @return MerchantShippingTemplate实体
     */
    ExpressTemplate toMerchantShippingTemplateEntity(ExpressTemplateModifyCmd cmd);

    /**
     * MerchantShippingTemplateDO转MerchantShippingTemplateCO
     *
     * @param templateDOs MerchantShippingTemplateDO列表
     * @return MerchantShippingTemplateCO
     */
    Page<ExpressTemplateCO> toMerchantShippingTemplateCO(Page<ExpressTemplateDO> templateDOs);

    /**
     * MerchantShippingTemplateDO转MerchantShippingTemplateCO
     *
     * @param templateDO MerchantShippingTemplateDO
     * @return MerchantShippingTemplateCO
     */
    ExpressTemplateCO toMerchantShippingTemplateCO(ExpressTemplateDO templateDO);

    /**
     * MerchantShippingTemplate转MerchantShippingTemplateCO
     *
     * @param template MerchantShippingTemplate
     * @return MerchantShippingTemplateCO
     */
    ExpressTemplateCO toMerchantShippingTemplateCO(ExpressTemplate template);
}
