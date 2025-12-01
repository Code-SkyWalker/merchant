package org.dromara.merchant.infrastructure.merchant.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantCO;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantCreateCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantModifyCmd;
import org.dromara.merchant.domain.merchant.model.Merchant;
import org.dromara.merchant.domain.merchant.model.MerchantCertify;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @Description 商户转换器
 * @Author Code Skywalker
 * @Date 2025/12/1 15:30
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MerchantConvertor {

    /**
     * Merchant实体转MerchantDO
     *
     * @param merchant Merchant实体
     * @return MerchantDO
     */
    MerchantDO toMerchantDO(Merchant merchant);

    /**
     * MerchantDO转Merchant实体
     *
     * @param merchantDO MerchantDO
     * @return Merchant实体
     */
    Merchant toMerchantEntity(MerchantDO merchantDO);

    /**
     * MerchantDO转Merchant实体
     *
     * @param certify 审批实体
     * @return Merchant实体
     */
    Merchant toMerchantEntity(MerchantCertify certify);

    /**
     * MerchantModifyCmd转Merchant实体
     *
     * @param cmd MerchantModifyCmd
     * @return Merchant实体
     */
    Merchant toMerchantEntity(MerchantModifyCmd cmd);

    /**
     * MerchantCreateCmd转Merchant实体
     *
     * @param cmd MerchantCreateCmd
     * @return Merchant实体
     */
    Merchant toMerchantEntity(MerchantCreateCmd cmd);

    /**
     * MerchantDO转MerchantCO
     *
     * @param merchantDOs MerchantDO列表
     * @return MerchantCO
     */
    Page<MerchantCO> toMerchantCO(Page<MerchantDO> merchantDOs);

    /**
     * MerchantDO转MerchantCO
     *
     * @param merchantDO MerchantDO
     * @return MerchantCO
     */
    MerchantCO toMerchantCO(MerchantDO merchantDO);
}
