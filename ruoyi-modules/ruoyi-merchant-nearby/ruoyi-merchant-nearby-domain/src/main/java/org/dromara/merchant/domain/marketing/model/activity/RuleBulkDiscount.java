package org.dromara.merchant.domain.marketing.model.activity;

import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Description N元N件模式
 * @Author Code Skywalker
 * @Date 2025/12/26 11:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleBulkDiscount implements Rule {

    private final String type = "BULK";

    /**
     * 满足金额
     */
    private BigDecimal amount;

    /**
     * 任选件数
     */
    private Integer unit;


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
     * @param unitPrice 商品单价
     * @param quantity  商品总数量
     * @return 优惠金额
     */
    @Override
    public BigDecimal calculate(BigDecimal unitPrice, Integer quantity) {
        // 判断商品数量是否满足优惠条件, 不满足则返回0
        if (quantity < this.unit) return BigDecimal.ZERO;

        // 计算优惠金额, 优惠金额 = 商品单价 * 优惠件数 - 满足金额
        BigDecimal discountAmount = unitPrice.multiply(BigDecimal.valueOf(this.unit)).subtract(this.amount);
        return discountAmount.compareTo(BigDecimal.ZERO) > 0 ? discountAmount : BigDecimal.ZERO;
    }
}
