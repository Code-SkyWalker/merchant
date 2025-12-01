package org.dromara.merchant.client.merchant.dto.data.command;

import lombok.Data;


/**
 * @Description 创建商户审批命令
 * @Author Code Skywalker
 * @Date 2025/12/1 16:30
 */
@Data
public class MerchantCertifyCreateCmd {

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
     * 审批类型: 0入驻审批 1修改审批
     */
    private Integer certifiedType;
}
