package org.dromara.merchant.domain.marketing.model;

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
}
