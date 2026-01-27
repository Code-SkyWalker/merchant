package org.dromara.merchant.infrastructure.address.mapper.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/1/16 16:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user_address")
public class AddressDO {

    /**
     * 地址ID
     */
    @TableId
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

}
