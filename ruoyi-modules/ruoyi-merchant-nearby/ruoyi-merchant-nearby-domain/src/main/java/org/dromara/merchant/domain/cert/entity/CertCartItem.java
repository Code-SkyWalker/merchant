package org.dromara.merchant.domain.cert.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 购物车项实体
 *
 * @author Lion Li
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertCartItem {


    /**
     * 商户ID
     */
    private Long merchantId;

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
     * 数量
     */
    private Integer quantity;

    /**
     * 优惠券ID列表
     */
    private List<Long> coupons;

    /**
     * 活动ID列表
     */
    private List<Long> marketings;


    /**
     * 增加数量
     */
    public void increaseQuantity() {
        this.quantity += 1;
    }

    /**
     * 减少数量
     */
    public void decreaseQuantity() {
        if (this.quantity <= 0) {
            this.quantity -= 1;
        }
    }
}
