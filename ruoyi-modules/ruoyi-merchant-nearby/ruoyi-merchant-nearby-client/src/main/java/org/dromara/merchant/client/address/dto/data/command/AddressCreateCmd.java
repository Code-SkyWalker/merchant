package org.dromara.merchant.client.address.dto.data.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 用户地址创建命令
 *
 * @author xian
 */
@Data
public class AddressCreateCmd {

    /**
     * 收货人姓名
     */
    @NotBlank(message = "收货人姓名不能为空")
    private String receiverName;

    /**
     * 收货人手机号
     */
    @NotBlank(message = "收货人手机号不能为空")
    private String receiverPhone;

    /**
     * 省份
     */
    @NotBlank(message = "省份不能为空")
    private String province;

    /**
     * 省份代码
     */
    @NotBlank(message = "省份代码不能为空")
    private String provinceCode;

    /**
     * 城市
     */
    @NotBlank(message = "城市不能为空")
    private String city;

    /**
     * 城市代码
     */
    @NotBlank(message = "城市代码不能为空")
    private String cityCode;

    /**
     * 区县
     */
    @NotBlank(message = "区县不能为空")
    private String district;

    /**
     * 区县代码
     */
    @NotBlank(message = "区县代码不能为空")
    private String districtCode;

    /**
     * 详细地址
     */
    @NotBlank(message = "详细地址不能为空")
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
    @NotBlank(message = "纬度坐标不能为空")
    private String latitude;

    /**
     * 经度坐标
     */
    @NotBlank(message = "经度坐标不能为空")
    private String longitude;
}
