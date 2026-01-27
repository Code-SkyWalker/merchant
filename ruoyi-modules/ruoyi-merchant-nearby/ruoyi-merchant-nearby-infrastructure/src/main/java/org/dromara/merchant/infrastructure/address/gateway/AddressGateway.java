package org.dromara.merchant.infrastructure.address.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.address.gateway.IAddressGateway;
import org.dromara.merchant.domain.address.model.Address;
import org.dromara.merchant.infrastructure.address.converter.AddressConverter;
import org.dromara.merchant.infrastructure.address.mapper.AddressMapper;
import org.dromara.merchant.infrastructure.address.mapper.dataobject.AddressDO;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/1/16 16:23
 */
@Component
@RequiredArgsConstructor
public class AddressGateway implements IAddressGateway {

    private final AddressMapper mapper;

    private final AddressConverter converter;

    /**
     * 保存地址信息
     *
     * @param address 地址信息
     * @return 是否保存成功
     */
    @Override
    public boolean save(Address address) {
        return this.mapper.insertOrUpdate(this.converter.toDO(address));
    }

    /**
     * 删除地址信息
     *
     * @param id 地址主键
     * @return 是否删除成功
     */
    @Override
    public boolean delete(Long id) {
        return this.mapper.deleteById(id) > 0;
    }

    /**
     * 根据ID查询地址信息
     *
     * @param id 地址ID
     * @return 地址信息
     */
    @Override
    public Address queryById(Long id) {
        AddressDO addressDO = this.mapper.selectById(id);
        return this.converter.toEntity(addressDO);
    }

    /**
     * 设置默认地址
     *
     * @param id     地址ID
     * @param userId 用户ID
     * @return 是否设置成功
     */
    @Override
    public Boolean setDefault(Long id, Long userId) {
        return this.mapper.setDefault(id, userId) > 0;
    }
}
