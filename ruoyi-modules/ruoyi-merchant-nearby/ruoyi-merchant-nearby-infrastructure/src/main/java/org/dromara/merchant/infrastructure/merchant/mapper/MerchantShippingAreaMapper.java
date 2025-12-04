package org.dromara.merchant.infrastructure.merchant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantShippingAreaDO;

import java.util.List;

/**
 * @Description 商户配送区域Mapper接口
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
public interface MerchantShippingAreaMapper extends BaseMapperPlus<MerchantShippingAreaDO, MerchantShippingAreaDO> {

    int deleteByPrimaryKey(Long areaId);

    int deleteByPrimaryKeys(@Param("areaIds") List<Long> areaIds);

    int insertOrUpdateSelective(MerchantShippingAreaDO record);

    int batchInsertOrUpdate(@Param("areas") List<MerchantShippingAreaDO> areas);

    MerchantShippingAreaDO selectByPrimaryKey(Long areaId);

    /**
     * 根据模板ID查询所有配送区域
     * @param templateId 模板ID
     * @return 配送区域列表
     */
    List<MerchantShippingAreaDO> selectByTemplateId(Long templateId);

    /**
     * 根据模板ID删除所有配送区域
     * @param templateId 模板ID
     * @return 删除记录数
     */
    int deleteByTemplateId(Long templateId);
}
