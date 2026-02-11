package org.dromara.merchant.client.cert.co;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * 商品信息CO
 *
 * @author Code Skywalker
 */
@Data
public class ProductInfoCO {

    /**
     * 商品ID(spuID)
     */
    private Long spuId;

    /**
     * 商品ID(skuID)
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品主图
     */
    private String mainImage;

    /**
     * 商品规格
     */
    private String specifications;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 优惠券列表
     */
    private List<CouponInfoCO> coupons = Collections.emptyList();

    /**
     * 活动信息
     */
    private List<ActivityInfoCO> marketings = Collections.emptyList();
}
