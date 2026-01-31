package org.dromara.merchant.infrastructure.marketing.converter;

import cn.hutool.json.JSONObject;
import org.dromara.merchant.client.marketing.dto.data.command.MarketingCreateCmd;
import org.dromara.merchant.client.marketing.dto.data.command.MarketingModifyCmd;
import org.dromara.merchant.domain.marketing.model.activity.*;
import org.dromara.merchant.infrastructure.marketing.mapper.dataobject.MarketingDO;
import org.mapstruct.*;

import java.util.List;

/**
 * @Description Marketing转换器
 * @Author Code Skywalker
 * @Date 2025/12/26 11:14
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MarketingConvertor {

    /**
     * Marketing实体转MarketingDO
     *
     * @param marketing Marketing实体
     * @return MarketingDO
     */
    @Mapping(target = "rules", ignore = true)
    MarketingDO toDo(Marketing marketing);

    /**
     * MarketingDO转Marketing实体
     *
     * @param marketingDO MarketingDO
     * @return Marketing实体
     */
    @Mapping(target = "rules", ignore = true)
    Marketing toEntity(MarketingDO marketingDO);

    /**
     * MarketingModifyCmd转Marketing实体
     *
     * @param cmd MarketingCreateCmd
     * @return Marketing实体
     */
    @Mapping(target = "rules", ignore = true)
    Marketing toEntity(MarketingModifyCmd cmd);


    @Mapping(target = "rules", ignore = true)
    List<Marketing> toMarketingList(List<MarketingDO> dos);

    /**
     * MarketingCreateCmd转Marketing实体
     *
     * @param cmd MarketingCreateCmd
     * @return Marketing实体
     */
    @Mapping(target = "rules", ignore = true)
    Marketing toEntity(MarketingCreateCmd cmd);

    @AfterMapping
    default void afterMappingEntityToDO(Marketing source, @MappingTarget MarketingDO target) {
        if (source.getRules() != null) {
            target.setRules(source.getRules().toJson());
        }
    }

    @AfterMapping
    default void afterMappingDOToEntity(MarketingDO source, @MappingTarget Marketing target) {
        Rule rule = convertRuleToEntity(source.getRules(), source.getType());
        if (rule != null) {
            target.setRules(rule);
        }
    }

    @AfterMapping
    default void afterMappingCmdToEntity(MarketingModifyCmd source, @MappingTarget Marketing target) {
        Rule rule = convertRuleToEntity(new JSONObject(source.getRules()).toString(), source.getType());
        if (rule != null) {
            target.setRules(rule);
        }
    }

    @AfterMapping
    default void afterMappingCmdToEntity(MarketingCreateCmd source, @MappingTarget Marketing target) {
        Rule rule = convertRuleToEntity(new JSONObject(source.getRules()).toString(), source.getType());
        if (rule != null) {
            target.setRules(rule);
        }
    }

    /**
     * 从规则JSON字符串和类型转换为具体的规则实体
     *
     * @param rulesJson 规则JSON字符串
     * @param type      营销类型
     * @return 具体的规则实体
     */
    default Rule convertRuleToEntity(String rulesJson, String type) {
        if (rulesJson == null) return null;

        MarketingType method = MarketingType.getByCode(type);
        if (method == null) return null;

        // 使用JSON序列化/反序列化来转换客户端对象到领域对象
        JSONObject jsonObject = new JSONObject(rulesJson);

        return switch (method) {
            case BULK -> jsonObject.toBean(RuleBulkDiscount.class);
            case MULTIUNIT -> jsonObject.toBean(RuleMultiUnitDiscount.class);
            case QUANTITY-> jsonObject.toBean(RuleQuantityDiscount.class);
            default -> null;
        };
    }
}
