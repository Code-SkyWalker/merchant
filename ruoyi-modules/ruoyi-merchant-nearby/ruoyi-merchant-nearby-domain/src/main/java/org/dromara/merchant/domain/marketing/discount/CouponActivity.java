package org.dromara.merchant.domain.marketing.discount;


import lombok.Data;
import org.dromara.merchant.domain.marketing.model.Coupon;
import org.dromara.merchant.domain.marketing.model.CouponSpu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/1/8 16:38
 */
@Data
public class CouponActivity implements Activities {

    /**
     * 折扣类型满减
     */
    public static final int DISCOUNT_TYPE_FULL_REDUCTION = 0;

    /**
     * 折扣类型满折
     */
    public static final int DISCOUNT_TYPE_FULL_DISCOUNT = 1;

    /**
     * 优惠券
     */
    private Coupon coupon;

    /**
     * 优惠券关联商品列表
     */
    private List<Product> associatedProducts = new ArrayList<>(); // 关联商品列表


    /**
     * 是否可用
     */
    @Override
    public boolean isApplicable() {
        return this.coupon.isApplicable()
            && calculateParticipateTotal().compareTo(this.coupon.getActuatingThreshold()) >=0;
    }

    /**
     * 计算优惠金额
     *
     * @return 优惠金额
     */
    @Override
    public BigDecimal calculateDiscount() {
        if (!isApplicable()) return BigDecimal.ZERO;

        // 满减, 直接返回优惠金额
        if (this.coupon.getDiscountType() == DISCOUNT_TYPE_FULL_REDUCTION) {
            return this.coupon.getDiscount();
        }

        // 满折, 返回折扣金额
        return calculateParticipateTotal().multiply(BigDecimal.TEN.subtract(this.coupon.getDiscount()));
    }

    private BigDecimal calculateParticipateTotal() {
        List<Long> spuIds = this.coupon.getCouponSpus().stream().map(CouponSpu::getSpuId).toList();
        return associatedProducts.stream()
            .filter(product -> spuIds.contains(product.getSpuId()))
            .map(product -> product.getOriginalPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
