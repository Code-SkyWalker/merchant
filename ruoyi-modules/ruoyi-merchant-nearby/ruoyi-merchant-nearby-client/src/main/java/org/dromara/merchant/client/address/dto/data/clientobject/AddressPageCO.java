package org.dromara.merchant.client.address.dto.data.clientobject;

import lombok.Data;

/**
 * 用户地址客户端对象
 *
 * @author xian
 */
@Data
public class AddressPageCO {

    /**
     * 地址ID
     */
    private Long addressId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人手机号
     */
    private String receiverPhone;

    /**
     * 是否默认地址 (0-否, 1-是)
     */
    private Integer isDefault;

    /**
     * 地址标签 (如：家、公司等)
     */
    private String addressLabel;

    /**
     * 详细地址
     */
    private String addressDetail;
}
