package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.merchant.gateway.IMerchantCertifyGateway;
import org.dromara.merchant.domain.merchant.gateway.IMerchantGateway;
import org.dromara.merchant.domain.merchant.model.MerchantCertify;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantConvertor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.dromara.merchant.domain.merchant.model.MerchantCertifyStatus.APPROVED;

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

    @Transactional(rollbackFor = Exception.class)
    public boolean execute(Long approvalId, String approvalStatus, String approvalComment) {
        MerchantCertify certify = this.gateway.findById(approvalId);
        if (certify == null) return false;

        // 如果审批通过，则保存商户信息
        if (APPROVED.name().equals(approvalStatus)) {
            this.merchantGateway.save(this.merchantConvertor.toMerchantEntity(certify));
        }

        return this.gateway.updateApprovalStatus(approvalId, approvalStatus, approvalComment);
    }

}
