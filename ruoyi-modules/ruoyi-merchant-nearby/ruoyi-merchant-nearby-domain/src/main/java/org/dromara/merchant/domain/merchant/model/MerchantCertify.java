package org.dromara.merchant.domain.merchant.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.dromara.common.core.utils.SnowflakeIdGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Description 商户审批实体
 * @Author Code Skywalker
 * @Date 2025/12/1 16:30
 */
@Data
@Accessors(chain = true)
public class MerchantCertify {

    private Long approvalId;

    /**
     * 商户ID
     */
    private Long merchantId = SnowflakeIdGenerator.generateId();

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 商户编码
     */
    private String merchantCode = "MERCHANT#" + this.merchantId;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 商户类型
     */
    private String merchantType;

    /**
     * 商户状态
     */
    private String status;

    /**
     * 商户等级
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
     * 审批类型: 0入驻审批 1修改审批
     */
    private Integer certifiedType;

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

    /**
     * 审批状态 (PENDING:待审批, APPROVED:审批通过, REJECTED:审批拒绝)
     */
    private String approvalStatus;

    /**
     * 审批意见
     */
    private String approvalComment;

    /**
     * 审批时间
     */
    private LocalDateTime approvalTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
