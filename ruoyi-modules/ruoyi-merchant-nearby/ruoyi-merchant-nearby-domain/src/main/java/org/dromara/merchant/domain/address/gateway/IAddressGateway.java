package org.dromara.merchant.domain.address.gateway;

import org.dromara.merchant.domain.address.model.Address;

/**
 * @Description 用户地址网关
 * @Author Code Skywalker
 * @Date 2026/1/16 16:25
 */
public interface IAddressGateway {

    /**
     * 保存地址信息
     *
     * @param address 地址信息
     * @return 是否保存成功
     */
    boolean save(Address address);

    /**
     * 删除地址信息
     *
     * @param id 地址主键
     * @return 是否删除成功
     */
    boolean delete(Long id);

    /**
     * 根据ID查询地址信息
     *
     * @param id 地址ID
     * @return 地址信息
     */
    Address queryById(Long id);

    /**
     * 设置默认地址
     *
     * @param id     地址ID
     * @param userId 用户ID
     * @return 是否设置成功
     */
    Boolean setDefault(Long id, Long userId);
}
