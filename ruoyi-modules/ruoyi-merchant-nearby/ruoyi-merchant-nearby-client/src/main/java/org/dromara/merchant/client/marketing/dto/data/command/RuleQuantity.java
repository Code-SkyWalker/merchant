package org.dromara.merchant.client.marketing.dto.data.command;

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
public class RuleQuantity implements Rule {

    /**
     * 满足数量
     */
    private Integer quantity;

    /**
     * 折扣
     */
    private BigDecimal discount;

}
