package org.dromara.express.infrastructure.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.express.client.dto.data.clientobject.ConfigCO;
import org.dromara.express.infrastructure.mapper.dataobject.ConfigDO;

/**
 * 快递配置Mapper接口
 */
public interface ConfigMapper extends BaseMapperPlus<ConfigDO, ConfigDO> {
    /**
     * 根据商家ID查询快递配置
     */
    ConfigCO queryByMerchantId(@Param("merchantId") Long merchantId);
}
