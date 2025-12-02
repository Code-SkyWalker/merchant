package org.dromara.merchant.domain.merchant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description 商家与分类关联实体
 * @Author Code Skywalker
 * @Date 2025/12/2 15:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MerchantCategory {

    private Long merchantId;
    private Long categoryId;
}
