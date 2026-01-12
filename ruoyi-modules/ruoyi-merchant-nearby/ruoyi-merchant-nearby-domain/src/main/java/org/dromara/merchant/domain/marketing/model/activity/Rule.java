package org.dromara.merchant.domain.marketing.model.activity;

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
     * 根据商品数量计算优惠金额
     *
     * @param unitPrice 商品总原价
     * @param quantity  商品总数量
     * @return 优惠金额
     */
    BigDecimal calculate(BigDecimal unitPrice, Integer quantity);


}
