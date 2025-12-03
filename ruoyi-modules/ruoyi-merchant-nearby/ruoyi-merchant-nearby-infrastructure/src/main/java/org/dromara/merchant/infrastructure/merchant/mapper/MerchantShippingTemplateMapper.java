package org.dromara.merchant.infrastructure.merchant.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantShippingTemplatePageQry;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantShippingTemplateDO;

import java.util.List;

/**
 * @Description 商户运费模板Mapper接口
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
public interface MerchantShippingTemplateMapper {

    int deleteByPrimaryKey(Long templateId);

    int insertOrUpdateSelective(MerchantShippingTemplateDO record);

    MerchantShippingTemplateDO selectByPrimaryKey(Long templateId);

    Page<MerchantShippingTemplateDO> selectPages(@Param("qry") MerchantShippingTemplatePageQry qry, @Param("page") Page<MerchantShippingTemplateDO> page);

    /**
     * 根据商户ID查询所有运费模板
     * @param merchantId 商户ID
     * @return 运费模板列表
     */
    List<MerchantShippingTemplateDO> selectByMerchantId(Long merchantId);

    /**
     * 根据商户ID和是否默认查询运费模板
     * @param merchantId 商户ID
     * @param isDefault 是否默认
     * @return 运费模板
     */
    MerchantShippingTemplateDO selectByMerchantIdAndIsDefault(@Param("merchantId") Long merchantId, @Param("isDefault") Boolean isDefault);

    /**
     * 取消商户的所有默认模板
     * @param merchantId 商户ID
     * @return 更新记录数
     */
    int cancelDefaultByMerchantId(Long merchantId);
}