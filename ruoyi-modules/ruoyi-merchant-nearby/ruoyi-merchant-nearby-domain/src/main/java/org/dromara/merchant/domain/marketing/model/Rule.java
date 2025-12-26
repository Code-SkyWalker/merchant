package org.dromara.merchant.domain.marketing.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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
    @JsonSubTypes.Type(value = RuleBulkDiscount.class, name = "bulk"),
    @JsonSubTypes.Type(value = RuleMultiUnitDiscount.class, name = "multi-unit"),
    @JsonSubTypes.Type(value = RuleQuantityDiscount.class, name = "quantity"),
})
public interface Rule {

    String toJson();
}
