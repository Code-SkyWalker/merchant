package org.dromara.huifu.domain.gateway;


import org.dromara.huifu.domain.model.HuifuConfig;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/13 16:42
 */
public interface IHuifuConfigGateway {

    /**
     * 创建汇付配置
     *
     * @param config 汇付配置
     * @return 是否成功
     */
    boolean save(HuifuConfig config);

    /**
     * 删除汇付配置
     *
     * @param huifuId 汇付ID
     * @return 是否成功
     */
    boolean delete(Long huifuId);

    /**
     * 根据租户编号查询
     *
     * @param tenantId 租户编号
     * @return 汇付配置
     */
    HuifuConfig queryByTenantId(String tenantId);

    /**
     * 查询渠道服务商配置
     *
     * @return 服务商配置
     */
    HuifuConfig queryChannelConfig();

    /**
     * 根据商户id 查询 商户配置
     *
     * @param merchantId 商户id
     * @return 商户配置
     */
    HuifuConfig queryByMerchantId(Long merchantId);


}
