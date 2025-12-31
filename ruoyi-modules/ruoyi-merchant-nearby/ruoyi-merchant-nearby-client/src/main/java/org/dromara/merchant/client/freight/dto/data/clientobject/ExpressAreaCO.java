package org.dromara.merchant.client.freight.dto.data.clientobject;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description 商户配送区域客户端对象
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
@Data
public class ExpressAreaCO {

    /**
     * 配送区域ID
     */
    private Long areaId;

    /**
     * 运费模板ID
     */
    private Long templateId;

    /**
     * 地区编码
     */
    private String adCode;

    /**
     * 首重/首件/首体积
     */
    private BigDecimal baseWeightQuantityVolume;

    /**
     * 首费
     */
    private BigDecimal baseFee;

    /**
     * 续重/续件/续体积
     */
    private BigDecimal additionalWeightQuantityVolume;

    /**
     * 续费
     */
    private BigDecimal additionalFee;

    /**
     * 排序
     */
    private Integer sort;
}
