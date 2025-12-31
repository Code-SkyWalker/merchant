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
    @JsonSubTypes.Type(value = RuleBulkDiscount.class, name = "BULK"),
    @JsonSubTypes.Type(value = RuleMultiUnitDiscount.class, name = "MULTIUNIT"),
    @JsonSubTypes.Type(value = RuleQuantityDiscount.class, name = "QUANTITY"),
})
public interface Rule {

    String type();

    String toJson();
}
