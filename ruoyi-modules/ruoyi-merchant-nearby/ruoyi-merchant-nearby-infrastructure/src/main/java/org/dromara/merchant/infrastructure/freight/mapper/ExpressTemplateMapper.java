package org.dromara.merchant.infrastructure.freight.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.client.freight.dto.data.clientobject.ExpressTemplateCO;
import org.dromara.merchant.client.freight.dto.data.command.query.ExpressTemplatePageQry;
import org.dromara.merchant.infrastructure.freight.mapper.dataobject.ExpressTemplateDO;

import java.util.List;

/**
 * @Description 商户运费模板Mapper接口
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
public interface ExpressTemplateMapper extends BaseMapperPlus<ExpressTemplateDO, ExpressTemplateDO> {

    ExpressTemplateCO selectByPrimaryKey(Long templateId);

    /**
     * 分页查询商户运费模板
     *
     * @param qry  查询参数
     * @param page 分页参数
     * @return 运费模板列表
     */
    Page<ExpressTemplateDO> selectPages(@Param("qry") ExpressTemplatePageQry qry, @Param("page") Page<ExpressTemplateDO> page);

    /**
     * 根据商户ID和是否默认查询运费模板
     *
     * @param merchantId 商户ID
     * @return 运费模板
     */
    ExpressTemplateDO selectByMerchantIdAndIsDefault(@Param("merchantId") Long merchantId);

    /**
     * 取消商户的所有默认模板
     *
     * @param merchantId 商户ID
     * @param templateId 运费模板ID
     * @return 更新记录数
     */
    int setDefaultByMerchantId(@Param("templateId") Long templateId, @Param("merchantId") Long merchantId);

    /**
     * 根据运费模板ID查询运费模板
     *
     * @param expressTemplateIds 运费模板ID
     * @return 运费模板列表
     */
    List<ExpressTemplateCO> selectByTemplateIds(@Param("expressTemplateIds") String[] expressTemplateIds);

}
