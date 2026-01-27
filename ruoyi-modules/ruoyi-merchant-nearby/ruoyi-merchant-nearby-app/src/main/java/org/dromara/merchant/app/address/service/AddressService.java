package org.dromara.merchant.app.address.service;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.address.IAddressService;
import org.dromara.merchant.app.address.executor.AddressCreateExe;
import org.dromara.merchant.app.address.executor.AddressDeleteExe;
import org.dromara.merchant.app.address.executor.AddressModifyExe;
import org.dromara.merchant.app.address.executor.AddressSetDefaultExe;
import org.dromara.merchant.client.address.dto.data.command.AddressCreateCmd;
import org.dromara.merchant.client.address.dto.data.command.AddressModifyCmd;
import org.dromara.merchant.domain.address.gateway.IAddressGateway;
import org.dromara.merchant.domain.address.model.Address;
import org.springframework.stereotype.Component;

/**
 * 用户地址Service实现类
 *
 * @author xian
 */
@Component
@RequiredArgsConstructor
public class AddressService implements IAddressService {

    private final AddressCreateExe addressCreateExe;
    private final AddressModifyExe addressModifyExe;
    private final AddressDeleteExe addressDeleteExe;
    private final AddressSetDefaultExe setDefaultExe;

    private final IAddressGateway addressGateway;


    /**
     * 新增用户地址
     *
     * @param cmd 用户地址创建命令
     * @return 新增成功后的地址ID
     */
    @Override
    public Boolean create(AddressCreateCmd cmd) {
        return this.addressCreateExe.execute(cmd);
    }

    /**
     * 修改用户地址
     *
     * @param cmd 用户地址修改命令
     * @return 修改成功返回true，否则返回false
     */
    @Override
    public Boolean modify(AddressModifyCmd cmd) {
        return this.addressModifyExe.execute(cmd);
    }

    /**
     * 删除用户地址
     *
     * @param addressId 地址ID
     * @return 删除成功返回true，否则返回false
     */
    @Override
    public Boolean deleteById(Long addressId) {
        return this.addressDeleteExe.execute(addressId);
    }

    /**
     * 设置默认地址
     *
     * @param addressId 地址ID
     * @return 设置成功返回true，否则返回false
     */
    @Override
    public Boolean setDefaultAddress(Long addressId) {
        return this.setDefaultExe.execute(addressId);
    }

    /**
     * 根据地址ID查询地址信息
     *
     * @param addressId 地址ID
     * @return 地址信息
     */
    @Override
    public Address queryById(Long addressId) {
        return this.addressGateway.queryById(addressId);
    }
}
