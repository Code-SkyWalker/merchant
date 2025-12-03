package org.dromara.merchant.infrastructure.merchant.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantShippingTemplateCO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantShippingTemplateCreateCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantShippingTemplateModifyCmd;
import org.dromara.merchant.domain.merchant.model.MerchantShippingTemplate;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantShippingTemplateDO;

/**
 * @Description 商户运费模板转换器
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MerchantShippingTemplateConvertor {

    /**
     * MerchantShippingTemplate实体转MerchantShippingTemplateDO
     *
     * @param template MerchantShippingTemplate实体
     * @return MerchantShippingTemplateDO
     */
    MerchantShippingTemplateDO toMerchantShippingTemplateDO(MerchantShippingTemplate template);

    /**
     * MerchantShippingTemplateDO转MerchantShippingTemplate实体
     *
     * @param templateDO MerchantShippingTemplateDO
     * @return MerchantShippingTemplate实体
     */
    MerchantShippingTemplate toMerchantShippingTemplateEntity(MerchantShippingTemplateDO templateDO);

    /**
     * MerchantShippingTemplateCreateCmd转MerchantShippingTemplate实体
     *
     * @param cmd MerchantShippingTemplateCreateCmd
     * @return MerchantShippingTemplate实体
     */
    MerchantShippingTemplate toMerchantShippingTemplateEntity(MerchantShippingTemplateCreateCmd cmd);

    /**
     * MerchantShippingTemplateModifyCmd转MerchantShippingTemplate实体
     *
     * @param cmd MerchantShippingTemplateModifyCmd
     * @return MerchantShippingTemplate实体
     */
    MerchantShippingTemplate toMerchantShippingTemplateEntity(MerchantShippingTemplateModifyCmd cmd);

    /**
     * MerchantShippingTemplateDO转MerchantShippingTemplateCO
     *
     * @param templateDOs MerchantShippingTemplateDO列表
     * @return MerchantShippingTemplateCO
     */
    Page<MerchantShippingTemplateCO> toMerchantShippingTemplateCO(Page<MerchantShippingTemplateDO> templateDOs);

    /**
     * MerchantShippingTemplateDO转MerchantShippingTemplateCO
     *
     * @param templateDO MerchantShippingTemplateDO
     * @return MerchantShippingTemplateCO
     */
    MerchantShippingTemplateCO toMerchantShippingTemplateCO(MerchantShippingTemplateDO templateDO);

    /**
     * MerchantShippingTemplate转MerchantShippingTemplateCO
     *
     * @param template MerchantShippingTemplate
     * @return MerchantShippingTemplateCO
     */
    MerchantShippingTemplateCO toMerchantShippingTemplateCO(MerchantShippingTemplate template);
}
