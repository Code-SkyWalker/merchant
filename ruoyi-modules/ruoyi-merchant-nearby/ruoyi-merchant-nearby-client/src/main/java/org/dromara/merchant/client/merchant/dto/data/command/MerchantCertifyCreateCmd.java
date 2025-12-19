package org.dromara.merchant.client.merchant.dto.data.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.core.xss.Xss;

import java.util.List;


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
    @Xss
    @NotBlank(message = "商户id", groups = {EditGroup.class})
    private Long merchantId;

    /**
     * 商户名称
     */
    @Xss
    @NotBlank(message = "商户名称不能为空")
    @Size(min = 1, max = 30, message = "用户账号长度不能超过{max}个字符")
    private String merchantName;

    /**
     * 商户类型
     *      PERSONAL("个人商户"),
     *      INDIVIDUAL("个体工商户"),
     *      ENTERPRISE("企业商户");
     */
    @Xss
    @NotBlank(message = "商户类型不能为空")
    private String merchantType;

    /**
     * 法人姓名
     */
    @NotBlank(message = "法人姓名不能为空")
    private String legalPerson;

    /**
     * 法人身份证号
     */
    @NotBlank(message = "法人身份证号不能为空")
    private String legalPersonIdNumber;

    /**
     * 法人身份证国徽面照片
     */
    @NotBlank(message = "法人身份证国徽面照片不能为空")
    private String legalPersonIdFront;

    /**
     * 法人身份证人像面照片
     */
    @NotBlank(message = "法人身份证人像面照片不能为空")
    private String legalPersonIdBack;

    /**
     * 营业执照
     */
    @NotBlank(message = "营业执照照片不能为空")
    private String businessLicense;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空")
    private String contactPhone;

    /**
     * 联系邮箱
     */
    private String contactEmail;

    /**
     * 经纬度
     */
    @NotBlank(message = "经纬度不能为空")
    private String preciseLocation;

    /**
     * 商户地址
     */
    @NotBlank(message = "商户地址不能为空")
    private String address;

    /**
     * 门头图片
     */
    @NotBlank(message = "门头图片不能为空")
    private String outerPicture;

    /**
     * 内部图片
     */
    @NotBlank(message = "内部图片不能为空")
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
     * 审批类型: 0入驻审批 1修改审批 2提货卡审批 3退出审批
     */
    @NotNull(message = "商户分类不能为空")
    private Integer certifiedType;


    /**
     * 商户分类
     */
    @NotEmpty(message = "商户分类不能为空")
    private List<Long> categoryIds;
}
