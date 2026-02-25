package org.dromara.merchant.domain.address.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.utils.SnowflakeIdGenerator;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.satoken.utils.LoginHelper;

/**
 * 用户地址实体类
 *
 * @author xian
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseEntity {

    /**
     * 地址ID
     */
    private Long addressId = SnowflakeIdGenerator.generateId();

    /**
     * 用户ID
     */
    private Long userId = LoginHelper.getUserId();

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
     * 获取地址(省、市、区)
     */
    public String getAddress() {
        return province + city + district;
    }
}
