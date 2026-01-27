package org.dromara.merchant.domain.marketing.model.activity;

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

    /**
     * 根据商品数量计算优惠金额
     *
     * @param unitPrice 商品总原价
     * @param quantity  商品总数量
     * @return 优惠金额
     */
    @Override
    public BigDecimal calculate(BigDecimal unitPrice, Integer quantity) {
        if (quantity < this.quantity) return BigDecimal.ZERO;

        // 购买数大于等于任选件数，返回 原价*(1-discount)（只有一件优惠）
        BigDecimal discount = this.discount.divide(BigDecimal.TEN, 2, RoundingMode.HALF_UP);
        BigDecimal discountTotal = unitPrice.multiply(BigDecimal.ONE.subtract(discount));
        return discountTotal.compareTo(BigDecimal.ZERO) > 0 ? discountTotal : BigDecimal.ZERO;

    }
}
