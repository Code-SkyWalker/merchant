package org.dromara.merchant.infrastructure.address.converter;

import org.dromara.merchant.client.address.dto.data.command.AddressCreateCmd;
import org.dromara.merchant.client.address.dto.data.command.AddressModifyCmd;
import org.dromara.merchant.domain.address.model.Address;
import org.dromara.merchant.infrastructure.address.mapper.dataobject.AddressDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/1/16 16:33
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressConverter {

    /**
     * DO转Entity
     * @param addressDO 地址数据对象
     * @return 地址实体
     */
    Address toEntity(AddressDO addressDO);

    /**
     * CreateCmd转Entity
      * @param cmd 地址创建命令
     * @return 地址实体
     */
    Address toEntity(AddressCreateCmd cmd);

    /**
     * ModifyCmd转Entity
      * @param cmd 地址修改命令
     * @return 地址实体
     */
    Address toEntity(AddressModifyCmd cmd);

    /**
     * Entity转DO
     * @param address 地址实体
     * @return 地址数据对象
     */
    AddressDO toDO(Address address);
}
