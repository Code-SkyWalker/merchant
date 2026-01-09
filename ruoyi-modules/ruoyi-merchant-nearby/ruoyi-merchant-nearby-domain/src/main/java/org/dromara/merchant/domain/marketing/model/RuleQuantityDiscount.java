package org.dromara.merchant.domain.marketing.model;

import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Description x件x折模式
 * @Author Code Skywalker
 * @Date 2025/12/26 11:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleQuantityDiscount implements Rule {

    private final String type = "QUANTITY";

    /**
     * 满足数量
     */
    private Integer quantity;

    /**
     * 折扣
     */
    private BigDecimal discount;

    @Override
    public String toJson() {
        return new JSONObject(this).toString();
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public BigDecimal calculate(BigDecimal originalUnitPrice, Integer quantity) {

        // 购买数小于等于任选件数，返回原价*购买数
        if (quantity < this.quantity) return BigDecimal.ZERO;

        // 购买数大于等于任选件数，返回 原价*(1-discount)（只有一件优惠）
        BigDecimal discountRate = originalUnitPrice.multiply(BigDecimal.ONE.subtract(this.discount.divide(BigDecimal.TEN, 4, RoundingMode.HALF_UP)));
        return originalUnitPrice.multiply(discountRate);

    }
}
