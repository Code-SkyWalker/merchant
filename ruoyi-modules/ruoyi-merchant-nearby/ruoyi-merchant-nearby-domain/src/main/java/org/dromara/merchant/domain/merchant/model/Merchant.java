package org.dromara.merchant.domain.merchant.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Description 商户实体 代表平台中的一个商户(卖家)
 * @Author Code Skywalker
 * @Date 2025/11/24 17:42
 */
@Data
@Accessors(chain = true)
public class Merchant {

    /**
     * 商户ID
     */
    private Long merchantId;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 商户编码
     */
    private String merchantCode;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 商户类型
     */
    private MerchantType merchantType;

    /**
     * 商户状态
     */
    private MerchantStatus status;

    /**
     * 商户等级
     */
    private MerchantLevel level;

    /**
     * 法人姓名
     */
    private String legalPerson;

    /**
     * 营业执照
     */
    private String businessLicense;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 联系邮箱
     */
    private String contactEmail;

    /**
     * 经纬度
     */
    private String preciseLocation;

    /**
     * 商户地址
     */
    private String address;

    /**
     * 门头图片
     */
    private String outerPicture;

    /**
     * 内部图片
     */
    private String innerPicture;

    /**
     * 商户logo
     */
    private String logo;

    /**
     * 商户描述
     */
    private String description;

    /**
     * 保证金
     */
    private BigDecimal depositAmount;

    /**
     * 结算周期(天)
     */
    private Integer settlementCycle;

    /**
     * 认证状态
     */
    private Boolean certified;

    /**
     * 认证时间
     */
    private LocalDateTime certifiedTime;

    /**
     * 入驻时间
     */
    private LocalDateTime joinTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 激活商户
     */
    public void activate() {
        this.status = MerchantStatus.ACTIVE;
    }

    /**
     * 禁用商户
     */
    public void disable() {
        this.status = MerchantStatus.DISABLED;
    }

    /**
     * 认证商户
     */
    public void certify() {
        this.certified = true;
        this.certifiedTime = LocalDateTime.now();
    }

    /**
     * 检查商户是否可用
     */
    public boolean isAvailable() {
        return status == MerchantStatus.ACTIVE && Boolean.TRUE.equals(certified);
    }

    /**
     * 升级商户等级
     */
    public void upgradeLevel(MerchantLevel newLevel) {
        if (newLevel.ordinal() > this.level.ordinal()) {
            this.level = newLevel;
        }
    }
}
