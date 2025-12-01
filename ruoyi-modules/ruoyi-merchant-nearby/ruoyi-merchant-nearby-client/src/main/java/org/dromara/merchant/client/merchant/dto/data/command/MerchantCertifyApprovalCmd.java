package org.dromara.merchant.client.merchant.dto.data.command;

import lombok.Data;


/**
 * @Description 修改商户审批命令
 * @Author Code Skywalker
 * @Date 2025/12/1 16:30
 */
@Data
public class MerchantCertifyApprovalCmd {

    /**
     * 商户审批ID
     */
    private Long approvalId;


    /**
     * 审批状态 (PENDING:待审批, APPROVED:审批通过, REJECTED:审批拒绝)
     */
    private String approvalStatus;

    /**
     * 审批意见
     */
    private String approvalComment;

}
