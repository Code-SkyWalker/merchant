package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.SnowflakeIdGenerator;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantCertifyCreateCmd;
import org.dromara.merchant.domain.merchant.gateway.IMerchantCategoryGateway;
import org.dromara.merchant.domain.merchant.gateway.IMerchantCertifyGateway;
import org.dromara.merchant.domain.merchant.gateway.IMerchantGateway;
import org.dromara.merchant.domain.merchant.model.MerchantCertify;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantCertifyConvertor;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantConvertor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 创建商户执行器
 * @Author Code Skywalker
 * @Date 2025/12/1 16:00
 */
@Component
@RequiredArgsConstructor
public class MerchantCertifyCreateExecutor {

    private final IMerchantCertifyGateway certifyGateway;
    private final IMerchantCategoryGateway merchantCategoryGateway;
    private final IMerchantGateway merchantGateway;

    private final MerchantCertifyConvertor certifyConvertor;
    private final MerchantConvertor merchantConvertor;

    @Transactional(rollbackFor = Exception.class)
    public boolean execute(MerchantCertifyCreateCmd cmd) {
        MerchantCertify certify = this.certifyConvertor.toMerchantCertifyEntity(cmd);

        if (certify.getMerchantId() == null) certify.setMerchantId(SnowflakeIdGenerator.generateId());

        // 如果是新增商户申请，则先保存商户
        if (certify.getCertifiedType() == 0) {
            // 保存商户分类
            this.merchantCategoryGateway.save(certify.getMerchantId(), cmd.getCategoryIds());

            // 保存商户
            this.merchantGateway.save(this.merchantConvertor.toMerchantEntity(certify));
        }

        // 保存商户申请
        return certifyGateway.save(certify);
    }

}
