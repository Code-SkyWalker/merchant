package org.dromara.merchant.domain.freight.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.dromara.common.core.utils.SnowflakeIdGenerator;
import org.dromara.common.tenant.core.TenantEntity;

import java.math.BigDecimal;

/**
 * @Description 商户配送区域及运费实体
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ExpressArea extends TenantEntity {

    /**
     * 配送区域ID
     */
    private Long areaId = SnowflakeIdGenerator.generateId();

    /**
     * 运费模板ID
     */
    private Long templateId;

    /**
     * 区划代码集合
     */
    private String adCode;

    /**
     * 首重/首件/首体积 (单位: kg/件/m³)
     */
    private BigDecimal baseWeightQuantityVolume;

    /**
     * 首费 (单位: 元)
     */
    private BigDecimal baseFee;

    /**
     * 续重/续件/续体积 (单位: kg/件/m³)
     */
    private BigDecimal additionalWeightQuantityVolume;

    /**
     * 续费 (单位: 元)
     */
    private BigDecimal additionalFee;

    /**
     * 排序
     */
    private Integer sort;

}
