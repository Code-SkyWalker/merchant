package org.dromara.merchant.infrastructure.merchant.mapper.dataobject;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.mybatis.core.domain.BaseEntity;

@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class MerchantShippingAreaDOtest extends BaseEntity {
    /**
    * 配送区域ID
    */
    private Integer areaId;

    /**
    * 运费模板ID
    */
    private Integer templateId;

    /**
    * 租户ID
    */
    private String tenantId;

    /**
    * 区划代码集合
    */
    private Object adcode;

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