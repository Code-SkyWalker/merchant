package org.dromara.huifu.infrastructure.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.huifu.infrastructure.mapper.dataobject.HuifuConfigDO;


@Mapper
public interface HuifuConfigMapper extends BaseMapperPlus<HuifuConfigDO, HuifuConfigDO> {

    /**
     * 根据merchantId查询
     *
     * @param merchantId 商户id
     * @return 汇付配置
     */
    HuifuConfigDO queryByMerchantId(@Param("merchantId") Long merchantId);

    /**
     * 查询汇付渠道商配置
     *
     * @return 汇付渠道商配置
     */
    HuifuConfigDO queryChannelConfig();

    /**
     * 根据租户编号查询
     *
     * @param tenantId 租户编号
     * @return 汇付配置
     */
    HuifuConfigDO queryByTenantId(String tenantId);
}
