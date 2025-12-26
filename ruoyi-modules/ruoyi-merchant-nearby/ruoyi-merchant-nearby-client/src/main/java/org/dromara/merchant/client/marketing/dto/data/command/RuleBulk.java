package org.dromara.merchant.client.marketing.dto.data.command;

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
public class RuleBulk implements Rule {

    /**
     * 满足金额
     */
    private BigDecimal amount;

    /**
     * 任选件数
     */
    private Integer unit;

}
