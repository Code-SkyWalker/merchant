package org.dromara.merchant.infrastructure.address.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.client.address.dto.data.clientobject.AddressCO;
import org.dromara.merchant.client.address.dto.data.clientobject.AddressPageCO;
import org.dromara.merchant.infrastructure.address.mapper.dataobject.AddressDO;

/**
 * 用户地址Mapper接口
 *
 * @author xian
 */
public interface AddressMapper extends BaseMapperPlus<AddressDO, AddressDO> {

    /**
     * 设置默认地址
     *
     * @param id     地址ID
     * @param userId 用户ID
     * @return 是否设置成功
     */
    int setDefault(@Param("id") Long id, @Param("userId") Long userId);

    /**
     * 主键查询详细地址
     *
     * @param id 主键
     * @return 地址详情
     */
    AddressCO queryAddressCOById(@Param("id") Long id);

    /**
     * 分页查询地址列表
     *
     * @param userId 用户ID
     * @param page   分页参数
     * @return 地址列表
     */
    Page<AddressPageCO> queryAddressCOPages(@Param("userId") Long userId, Page<AddressCO> page);
}
