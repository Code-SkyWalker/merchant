package org.dromara.merchant.adapter.web.address;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.merchant.app.address.IAddressService;
import org.dromara.merchant.client.address.dto.data.clientobject.AddressCO;
import org.dromara.merchant.client.address.dto.data.clientobject.AddressPageCO;
import org.dromara.merchant.client.address.dto.data.command.AddressCreateCmd;
import org.dromara.merchant.client.address.dto.data.command.AddressModifyCmd;
import org.dromara.merchant.infrastructure.address.mapper.AddressMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户地址
 *
 * @author Code Skywalker
 */
@RestController
@RequestMapping("/merchant/address")
@RequiredArgsConstructor
public class AddressController {

    private final IAddressService addressService;

    private final AddressMapper addressMapper;

    /**
     * 分页查询用户地址
     */
    @GetMapping("/page/{userId}")
    public TableDataInfo<AddressPageCO> page(@PathVariable Long userId, @ModelAttribute PageQuery page) {
        return TableDataInfo.build(addressMapper.queryAddressCOPages(userId, page.build()));
    }

    /**
     * 获取用户地址详情
     */
    @GetMapping("/{addressId}")
    public R<AddressCO> getInfo(@PathVariable Long addressId) {
        return R.ok(addressMapper.queryAddressCOById(addressId));
    }


    /**
     * 新增用户地址
     */
    @PostMapping
    public R<Boolean> add(@Validated @RequestBody AddressCreateCmd cmd) {
        Boolean result = addressService.create(cmd);
        return result ? R.ok() : R.fail();
    }

    /**
     * 修改用户地址
     */
    @PutMapping
    public R<Boolean> edit(@Validated @RequestBody AddressModifyCmd cmd) {
        Boolean result = addressService.modify(cmd);
        return result ? R.ok() : R.fail();
    }

    /**
     * 删除用户地址
     */
    @DeleteMapping("/{addressId}")
    public R<Boolean> remove(@PathVariable Long addressId) {
        Boolean result = addressService.deleteById(addressId);
        return result ? R.ok() : R.fail();
    }

    /**
     * 设置默认地址
     */
    @PutMapping("/setDefault/{addressId}")
    public R<Boolean> setDefault(@PathVariable Long addressId) {
        Boolean result = addressService.setDefaultAddress(addressId);
        return result ? R.ok() : R.fail();
    }
}
