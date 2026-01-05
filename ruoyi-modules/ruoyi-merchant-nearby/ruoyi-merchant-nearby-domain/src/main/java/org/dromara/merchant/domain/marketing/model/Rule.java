package org.dromara.merchant.domain.marketing.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.math.BigDecimal;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/26 11:23
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = RuleBulkDiscount.class, name = "BULK"),
    @JsonSubTypes.Type(value = RuleMultiUnitDiscount.class, name = "MULTIUNIT"),
    @JsonSubTypes.Type(value = RuleQuantityDiscount.class, name = "QUANTITY"),
})
public interface Rule {

    /**
     * 获取规则类型
     *
     * @return 规则类型
     */
    String type();

    /**
     * 转为JSON
     *
     * @return JSON
     */
    String toJson();

    /**
     * 计算优惠后的价格
     *
     * @param originalUnitPrice 单件原价
     * @param quantity          购买数量
     * @return 优惠后的价格
     */
    BigDecimal calculate(BigDecimal originalUnitPrice, Integer quantity);

}
