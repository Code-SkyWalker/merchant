package org.dromara.huifu.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.common.core.utils.SnowflakeIdGenerator;

import java.math.BigDecimal;

/**
 * @Description 分账比例实体类
 * @Author Code Skywalker
 * @Date 2025/11/14 13:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HuifuFeeRate {

    /**
     * 分账比例ID
     */
    private Long rateId = SnowflakeIdGenerator.generateId();

    /**
     * 分账比例（百分比值）
     */
    private BigDecimal feeRate;

    /**
     * 比例类别：0：普通商家 1：运营平台（租户） 2：汇付渠道商（益巨科技）
     */
    private Integer type;

    /**
     * 商家ID
     */
    private Long merchantId;

    public HuifuFeeRate(Long merchantId, BigDecimal feeRate, Integer type) {
        this.feeRate = feeRate;
        this.type = type;
        this.merchantId = merchantId;
    }
}
