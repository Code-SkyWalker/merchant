package org.dromara.merchant.domain.marketing.model.activity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.common.core.utils.SnowflakeIdGenerator;
import org.dromara.merchant.domain.marketing.discount.Activities;
import org.dromara.merchant.domain.marketing.discount.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Description 优惠券领域对象
 * @Author Code Skywalker
 * @Date 2025/12/29 10:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon implements Activities {

    /**
     * 折扣类型满减
     */
    public static final int DISCOUNT_TYPE_FULL_REDUCTION = 0;

    /**
     * 折扣类型满折
     */
    public static final int DISCOUNT_TYPE_FULL_DISCOUNT = 1;

    /**
     * 优惠券主键
     */
    private Long id = SnowflakeIdGenerator.generateId();

    /**
     * 优惠券名称
     */
    private String name;

    /**
     * 领取时间（开始）
     */
    private LocalDateTime receiveBegin;

    /**
     * 领取时间（结束）
     */
    private LocalDateTime receiveEnd;

    /**
     * 券使用时间（开始）
     */
    private LocalDateTime serviceBegin;

    /**
     * 券使用时间（结束）
     */
    private LocalDateTime serviceEnd;

    /**
     * 使用范围：'ONLINE'线上，'OFFLINE'线下，'NON_LIMIT'无限制
     */
    private CouponType actuatingRange;

    /**
     * 使用门槛：0无门槛
     */
    private BigDecimal actuatingThreshold;

    /**
     * 活动优惠类型：0满减元 1满打折
     */
    private Integer discountType;

    /**
     * 活动优惠额度
     */
    private BigDecimal discount;

    /**
     * 发放张数
     */
    private Integer grantTotal;

    /**
     * 领取数
     */
    private Integer receiveCount;

    /**
     * 商品作用范围：'ALL'所有商品,'INCLUDE'指定商品,'EXCLUDE'排除商品
     */
    private CouponScope goodsRange;

    /**
     * 商家Id
     */
    private Long merchantId;

    /**
     * 优惠券与商品关联 列表
     */
    List<CouponSpu> couponSpus;

    /**
     * 计算优惠券可用的总金额
     * @param originPriceTotal 商品总原价
     * @return 优惠后的总佣金
     */
    public BigDecimal calculateDiscount(BigDecimal originPriceTotal) {
        if (originPriceTotal == null || originPriceTotal.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        // 如果是满减，直接返回优惠金额（需确保不超过商品总价）
        if (this.discountType == DISCOUNT_TYPE_FULL_REDUCTION) {
            if (this.discount != null && this.discount.compareTo(originPriceTotal) > 0) {
                return originPriceTotal; // 优惠金额不能超过商品总价
            }
            return this.discount != null ? this.discount : BigDecimal.ZERO;
        }

        // 如果是满折，计算折扣金额
        if (this.discount != null && this.discount.compareTo(BigDecimal.ZERO) > 0) {
            // 将折扣值转换为折扣率，例如：8折 = 0.8
            BigDecimal discountRate = this.discount.divide(BigDecimal.TEN, 2, RoundingMode.HALF_UP);
            BigDecimal discountedPrice = originPriceTotal.multiply(discountRate);
            BigDecimal discountAmount = originPriceTotal.subtract(discountedPrice);
            return discountAmount.compareTo(BigDecimal.ZERO) > 0 ? discountAmount : BigDecimal.ZERO;
        }

        return BigDecimal.ZERO;
    }

    @Override
    public boolean isApplicable(List<Product> products, Map<Product, BigDecimal> currentPrices) {
        LocalDateTime now = LocalDateTime.now();
        return
            // 优惠券是否在可用时间范围内且不为线下使用
            this.serviceBegin.isBefore(now) && this.serviceEnd.isAfter(now)
                // 且不为线下使用
                && !CouponType.OFFLINE.equals(this.actuatingRange)
                // 商品总金额是否达到使用门槛
                && calculateEligibleTotal(products, currentPrices).compareTo(this.actuatingThreshold) >= 0;
    }

    /**
     * 计算优惠券可用的总金额
     *
     * @param products      商品列表
     * @param currentPrices 当前商品价格
     * @return 可用的总金额
     */
    @Override
    public BigDecimal calculateTotalDiscount(List<Product> products, Map<Product, BigDecimal> currentPrices) {
        // 如果未达到门槛，返回0
        if (!isApplicable(products, currentPrices)) return BigDecimal.ZERO;

        // 计算符合条件商品的总价值，然后优惠金额
        BigDecimal eligibleTotal = calculateEligibleTotal(products, currentPrices);
        return this.calculateDiscount(eligibleTotal);
    }

    /**
     * 判断商品是否适用于当前优惠券
     *
     * @param product 商品
     * @return 是否参与优惠
     */
    @Override
    public boolean isProductEligible(Product product) {
        // 判断商品是否在优惠券的指定商品列表中
        boolean included = this.couponSpus.stream()
            .anyMatch(couponSpu -> couponSpu.getSpuId().equals(product.getSpuId()));

        // 如果优惠券作用范围是排除的商品，则将included 取反
        if (CouponScope.EXCLUDE.equals(this.goodsRange)) {
            included = !included;
        }
        return included;
    }


}
