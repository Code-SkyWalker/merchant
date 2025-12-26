package org.dromara.merchant.domain.marketing.model;

import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Description x件x折模式
 * @Author Code Skywalker
 * @Date 2025/12/26 11:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleQuantityDiscount implements Rule {

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
}
