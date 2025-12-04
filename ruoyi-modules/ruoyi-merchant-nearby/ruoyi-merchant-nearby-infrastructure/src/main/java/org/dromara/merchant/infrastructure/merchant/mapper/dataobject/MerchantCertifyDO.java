package org.dromara.merchant.infrastructure.merchant.mapper.dataobject;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * @Description 商户审批表DO对象
 * @Author Code Skywalker
 * @Date 2025-12-01${TIME}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_merchant_certify")
public class MerchantCertifyDO extends BaseEntity {

    /**
     * 审批ID
     */
    @TableId
    private Long approvalId;

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
     * 入驻时间
     */
    private LocalDateTime joinTime;

    /**
     * 审批类型: 0入驻审批 1修改审批
     */
    private Integer certifiedType;

    /**
     * 审批状态: PENDING待审批 APPROVED审批通过 REJECTED审批拒绝
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
}
