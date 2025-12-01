package org.dromara.merchant.infrastructure.merchant.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantCertifyCO;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantCertifyCreateCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantCertifyModifyCmd;
import org.dromara.merchant.domain.merchant.model.MerchantCertify;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantCertifyDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * @Description 商户审批转换器
 * @Author Code Skywalker
 * @Date 2025/12/1 16:30
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MerchantCertifyConvertor {

    /**
     * MerchantCertify实体转MerchantCertifyDO
     *
     * @param merchantCertify MerchantCertify实体
     * @return MerchantCertifyDO
     */
    MerchantCertifyDO toMerchantCertifyDO(MerchantCertify merchantCertify);

    /**
     * MerchantCertifyDO转MerchantCertify实体
     *
     * @param merchantCertifyDO MerchantCertifyDO
     * @return MerchantCertify实体
     */
    MerchantCertify toMerchantCertifyEntity(MerchantCertifyDO merchantCertifyDO);

    List<MerchantCertify> toMerchantCertifyEntity(List<MerchantCertifyDO> merchantCertifyDO);

    /**
     * MerchantCertifyCreateCmd转MerchantCertify实体
     *
     * @param cmd MerchantCertifyCreateCmd
     * @return MerchantCertify实体
     */
    MerchantCertify toMerchantCertifyEntity(MerchantCertifyCreateCmd cmd);

    /**
     * MerchantCertifyModifyCmd转MerchantCertify实体
     *
     * @param cmd MerchantCertifyModifyCmd
     * @return MerchantCertify实体
     */
    MerchantCertify toMerchantCertifyEntity(MerchantCertifyModifyCmd cmd);

    /**
     * MerchantCertifyDO转MerchantCertifyCO
     *
     * @param merchantCertifyDOs MerchantCertifyDO列表
     * @return MerchantCertifyCO
     */
    Page<MerchantCertifyCO> toMerchantCertifyCO(Page<MerchantCertifyDO> merchantCertifyDOs);

    /**
     * MerchantCertifyDO转MerchantCertifyCO
     *
     * @param merchantCertifyDO MerchantCertifyDO
     * @return MerchantCertifyCO
     */
    MerchantCertifyCO toMerchantCertifyCO(MerchantCertifyDO merchantCertifyDO);

    /**
     * MerchantCertify转MerchantCertifyCO
     *
     * @param merchantCertify MerchantCertify
     * @return MerchantCertifyCO
     */
    MerchantCertifyCO toMerchantCertifyCO(MerchantCertify merchantCertify);
}
