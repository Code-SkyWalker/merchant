package org.dromara.merchant.infrastructure.commodity.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.SkuDO;

public interface SkuMapper extends BaseMapperPlus<SkuDO, SkuDO> {

    List<SkuDO> selectBySpuId(@Param("spuId")Long spuId);


    int deleteBySpuId(@Param("spuId") Long spuId);
}
