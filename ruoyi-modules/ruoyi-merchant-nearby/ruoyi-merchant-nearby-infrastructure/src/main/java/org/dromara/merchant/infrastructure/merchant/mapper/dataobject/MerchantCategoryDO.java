package org.dromara.merchant.infrastructure.merchant.mapper.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * @Description 商户分类关联表DO对象
 * @Author Code Skywalker
 * @Date 2025-12-02 ${TIME}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_merchant_category")
public class MerchantCategoryDO extends BaseEntity {
    /**
     * 商户ID
     */
    private Long merchantId;

    /**
     * 分类ID
     */
    private Long categoryId;
}
