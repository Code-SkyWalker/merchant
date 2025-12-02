package org.dromara.merchant.infrastructure.merchant.mapper.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Description 商户DO对象
 * @Author Code Skywalker
 * @Date 2025/12/1 15:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MerchantDO extends BaseEntity {

    /**
     * 商户ID
     */
    private Long merchantId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 商户编码
     */
    private String merchantCode;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 商户类型 (PERSONAL:个人商户, INDIVIDUAL:个体工商户, ENTERPRISE:企业商户)
     */
    private String merchantType;

    /**
     * 商户状态 (PENDING:待审核, ACTIVE:激活, REPOSE:休眠, DISABLED:禁用, FROZEN:冻结)
     */
    private String status;

    /**
     * 商户等级 (NORMAL:普通商户, SILVER:银牌商户, GOLD:金牌商户, DIAMOND:钻石商户)
     */
    private String level;

    /**
     * 法人姓名
     */
    private String legalPerson;

    /**
     * 法人身份证号
     */
    private String legalPersonIdNumber;

    /**
     * 法人身份证国徽面照片
     */
    private String legalPersonIdFront;

    /**
     * 法人身份证人像面照片
     */
    private String legalPersonIdBack;

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
     * 认证状态 (0:未认证, 1:已认证)
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

}
