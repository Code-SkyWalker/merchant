package org.dromara.merchant.client.marketing.dto.data.command;

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
    @JsonSubTypes.Type(value = RuleBulk.class, name = "bulk"),
    @JsonSubTypes.Type(value = RuleMultiUnit.class, name = "multi-unit"),
    @JsonSubTypes.Type(value = RuleQuantity.class, name = "quantity"),
})
public interface Rule {

}
