package org.dromara.merchant.app.address;

import org.dromara.merchant.client.address.dto.data.command.AddressCreateCmd;
import org.dromara.merchant.client.address.dto.data.command.AddressModifyCmd;
import org.dromara.merchant.domain.address.model.Address;

/**
 * 用户地址Service接口
 *
 * @author xian
 */
public interface IAddressService {

    /**
     * 新增用户地址
     *
     * @param cmd 用户地址创建命令
     * @return 新增成功后的地址ID
     */
    Boolean create(AddressCreateCmd cmd);

    /**
     * 修改用户地址
     *
     * @param cmd 用户地址修改命令
     * @return 修改成功返回true，否则返回false
     */
    Boolean modify(AddressModifyCmd cmd);

    /**
     * 删除用户地址
     *
     * @param addressId 地址ID
     * @return 删除成功返回true，否则返回false
     */
    Boolean deleteById(Long addressId);

    /**
     * 设置默认地址
     *
     * @param addressId 地址ID
     * @return 设置成功返回true，否则返回false
     */
    Boolean setDefaultAddress(Long addressId);

    /**
     * 根据地址ID查询地址信息
     *
     * @param addressId 地址ID
     * @return 地址信息
     */
    Address queryById(Long addressId);
}
