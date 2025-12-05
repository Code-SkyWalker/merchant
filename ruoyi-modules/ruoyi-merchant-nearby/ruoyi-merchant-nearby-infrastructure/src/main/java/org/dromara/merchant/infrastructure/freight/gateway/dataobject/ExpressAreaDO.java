package org.dromara.merchant.infrastructure.freight.gateway.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * @Description 商户配送区域及运费DO对象
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("tb_merchant_shipping_area")
public class ExpressAreaDO extends BaseEntity {

    /**
     * 配送区域ID
     */
    @TableId
    private Long areaId;

    /**
     * 运费模板ID
     */
    private Long templateId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 区划代码集合
     */
    @TableField("adcode")
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
