package org.dromara.merchant.domain.marketing.model;

import lombok.Data;

/**
 * @Description 营销活动与商品关联实体
 * @Author Code Skywalker
 * @Date 2025/12/29 11:32
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
     * 删除标识
     */
    private Boolean deleted = false;

}
