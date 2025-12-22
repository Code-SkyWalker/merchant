package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.merchant.gateway.IMerchantCertifyGateway;
import org.dromara.merchant.domain.merchant.gateway.IMerchantGateway;
import org.dromara.merchant.domain.merchant.model.Merchant;
import org.dromara.merchant.domain.merchant.model.MerchantCertify;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantConvertor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.dromara.merchant.domain.merchant.model.CertifyStatus.*;

/**
 * @Description
 * @Author Code Skywalker
 * @Date 2025/12/1 16:00
 */
@Component
@RequiredArgsConstructor
public class MerchantCertifyApprovalExecutor {

    private final IMerchantCertifyGateway gateway;

    private final IMerchantGateway merchantGateway;

    private final MerchantConvertor merchantConvertor;


    /**
     * 执行商户认证审批
     *
     * @param approvalId 审批ID
     * @param approvalStatus 审批状态
     * @param approvalComment 审批意见
     * @return 执行结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean execute(Long approvalId, String approvalStatus, String approvalComment) {
        // 1. 查询认证信息，不存在则返回失败
        MerchantCertify certify = this.gateway.findById(approvalId);
        if (certify == null) {
            return false;
        }

        // 2. 处理审批驳回场景
        if (REJECTED.name().equals(approvalStatus)) {
            return handleRejection(certify, approvalComment);
        }

        // 3. 处理审批通过场景
        if (APPROVED.name().equals(approvalStatus)) {
            return handleApproval(certify);
        }

        // 5. 处理审批取消场景
        if (CANCEL.name().equals(approvalStatus)) {
            return handelCancel(certify);
        }

        // 5. 非法审批状态，返回失败
        return false;
    }

    private boolean handelCancel(MerchantCertify certify) {
        certify.approvalCancel();
        return this.gateway.save(certify);
    }

    /**
     * 处理审批驳回逻辑
     *
     * @param certify 认证信息
     * @param approvalComment 审批意见
     * @return 处理结果
     */
    private boolean handleRejection(MerchantCertify certify, String approvalComment) {
        // 更新认证状态为驳回
        certify.approvalFail(approvalComment);
        return this.gateway.save(certify);
    }

    /**
     * 处理审批通过逻辑
     *
     * @param certify 认证信息
     * @return 处理结果
     */
    private boolean handleApproval(MerchantCertify certify) {
        // 更新认证状态为通过
        certify.approvalPass();
        boolean certifySaved = this.gateway.save(certify);

        // 转换并更新商户信息
        Merchant merchant = this.merchantConvertor.toMerchantEntity(certify);

        // 如果是首次认证（认证类型为0），则标记商户为已认证
        if (certify.getCertifiedType().equals(0)) {
            merchant.certify();
        }

        // 如果是退出认证，则标记商户为禁用状态
        if (certify.getCertifiedType().equals(3)) {
            merchant.disable();
        }

        // 保存商户信息，两者都成功才返回true
        boolean merchantSaved = this.merchantGateway.save(merchant);
        return certifySaved && merchantSaved;
    }

}
