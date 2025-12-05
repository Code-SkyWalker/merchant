package org.dromara.merchant.infrastructure.freight.mapper;

import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.infrastructure.freight.gateway.dataobject.ExpressAreaDO;

import java.util.List;

/**
 * @Description 商户配送区域Mapper接口
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
public interface ExpressAreaMapper extends BaseMapperPlus<ExpressAreaDO, ExpressAreaDO> {


    /**
     * 根据模板ID查询所有配送区域
     * @param templateId 模板ID
     * @return 配送区域列表
     */
    List<ExpressAreaDO> selectByTemplateId(Long templateId);

    /**
     * 根据模板ID删除所有配送区域
     * @param templateId 模板ID
     * @return 删除记录数
     */
    int deleteByTemplateId(Long templateId);
}
