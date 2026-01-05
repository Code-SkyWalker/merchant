package org.dromara.merchant.client.marketing.dto.data.command;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/30 09:41
 */
@Data
public class MarketingSpu {

    /**
     * 营销活动与商品关联ID
     */
    private Long id;

    /**
     * 活动主键
     */
    private Long marketingId;

    /**
     * spuid
     */
    private Long spuId;

    /**
     * 秒杀价格 仅秒杀活动使用
     */
    private BigDecimal killPrice;

    /**
     * 删除标识
     */
    private Boolean deleted = false;


}
