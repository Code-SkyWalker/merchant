package org.dromara.merchant.domain.marketing.discount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Description sku计算实体
 * @Author Code Skywalker
 * @Date 2026/1/8 17:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long spuId;
    private Long skuId;
    private BigDecimal originalPrice;
    private int quantity;

    public BigDecimal getTotalPrice() {
        return originalPrice.multiply(BigDecimal.valueOf(quantity));
    }

}
