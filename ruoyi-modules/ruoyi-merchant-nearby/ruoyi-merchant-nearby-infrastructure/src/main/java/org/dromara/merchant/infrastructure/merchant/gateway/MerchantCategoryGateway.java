package org.dromara.merchant.infrastructure.merchant.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.merchant.gateway.IMerchantCategoryGateway;
import org.dromara.merchant.domain.merchant.model.MerchantCategory;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantCategoryConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.MerchantCategoryMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 商家网关接口
 * @Author Code Skywalker
 * @Date 2025-10-23 11:37
 */
@Component
@RequiredArgsConstructor
public class MerchantCategoryGateway implements IMerchantCategoryGateway {

    private final MerchantCategoryMapper mapper;
    private final MerchantCategoryConvertor convertor;

    /**
     * 保存商户与分类关系
     *
     * @param merchantId 商户ID
     * @param categoryIds 商户分类ID
     * @return 是否保存成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean save(Long merchantId, List<Long> categoryIds) {
        if (merchantId == null || categoryIds == null || categoryIds.isEmpty()) {
            return false;
        }

        List<MerchantCategory> merchantCategories = categoryIds.stream()
            .map(categoryId -> new MerchantCategory(merchantId, categoryId))
            .toList();

        this.mapper.deleteByMerchantId(merchantId);
        return mapper.batchInsertOrUpdate(this.convertor.toMerchantCategoryDO(merchantCategories)) > 0;
    }

}
