package org.dromara.merchant.client.address.dto.data.clientobject;

import lombok.Data;

/**
 * 用户地址客户端对象
 *
 * @author xian
 */
@Data
public class AddressCO {

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
     * 省份
     */
    private String province;

    /**
     * 省份代码
     */
    private String provinceCode;

    /**
     * 城市
     */
    private String city;

    /**
     * 城市代码
     */
    private String cityCode;

    /**
     * 区县
     */
    private String district;

    /**
     * 区县代码
     */
    private String districtCode;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 邮政编码
     */
    private String postalCode;

    /**
     * 是否默认地址 (0-否, 1-是)
     */
    private Integer isDefault;

    /**
     * 地址标签 (如：家、公司等)
     */
    private String addressLabel;

    /**
     * 纬度坐标
     */
    private String latitude;

    /**
     * 经度坐标
     */
    private String longitude;

    /**
     * 完整地址（省份+城市+区县+详细地址）
     */
    public String getFullAddress() {
        StringBuilder sb = new StringBuilder();
        if (province != null) sb.append(province);
        if (city != null) sb.append(city);
        if (district != null) sb.append(district);
        if (detailAddress != null) sb.append(detailAddress);
        return sb.toString();
    }
}
