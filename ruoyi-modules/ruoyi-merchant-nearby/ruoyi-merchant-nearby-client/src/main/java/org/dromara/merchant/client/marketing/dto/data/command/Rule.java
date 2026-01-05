package org.dromara.merchant.client.marketing.dto.data.command;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.lang.model.type.NullType;

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
    @JsonSubTypes.Type(value = RuleBulk.class, name = "BULK"),
    @JsonSubTypes.Type(value = RuleMultiUnit.class, name = "MULTIUNIT"),
    @JsonSubTypes.Type(value = RuleQuantity.class, name = "QUANTITY")
})
public interface Rule {

}
