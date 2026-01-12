package org.dromara.merchant.domain.marketing.service;

import lombok.Data;
import org.dromara.merchant.domain.marketing.discount.CalculationContext;
import org.dromara.merchant.domain.marketing.discount.CalculationResult;
import org.dromara.merchant.domain.marketing.discount.Product;
import org.dromara.merchant.domain.marketing.model.activity.Marketing;
import org.dromara.merchant.domain.marketing.model.coupon.Coupon;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 定价引擎类 使用责任链模式处理各种优惠活动和优惠券
 * @Author Code Skywalker
 * @Date 2026/1/12 17:48
 */
@Data
public class PricingEngine {


    /**
     * 优惠活动列表
     */
    private List<Marketing> marketings = new ArrayList<>();

    /**
     * 优惠券列表
     */
    private List<Coupon> coupons = new ArrayList<>();

    /**
     * 运费
     */
    private BigDecimal shippingFee = BigDecimal.ZERO;

    /**
     * 责任链处理器
     */
    private PriceCalculator calculatorChain;

    /**
     * 积分抵扣金额
     */
    private BigDecimal integralDiscountAmount;

    /**
     * 佣金抵扣金额
     */
    private BigDecimal commDiscountAmount;

    /**
     * 默认构造函数
     * 初始化定价引擎并构建责任链
     */
    public PricingEngine() {
        buildCalculatorChain();
    }

    /**
     * 构建责任链
     * 按照处理顺序连接各个计算器
     */
    private void buildCalculatorChain() {
        CouponCalculator couponCalculator = new CouponCalculator();           // 阶梯价活动处理器
        MarketingCalculator marketingCalculator = new MarketingCalculator();   // 单品券处理器

        // 构建责任链：优惠券 -> 优惠活动
        couponCalculator.setNext(marketingCalculator);

        this.calculatorChain = couponCalculator;
    }

    /**
     * 添加优惠活动
     * @param marketing 优惠活动
     */
    public void addActivity(Marketing marketing) {
        marketings.add(marketing);
    }

    /**
     * 添加优惠券
     * @param coupon 优惠券
     */
    public void addCoupon(Coupon coupon) {
        coupons.add(coupon);
    }

    /**
     * 计算最终价格
     * @param products 商品列表
     * @return 计算结果
     */
    public CalculationResult calculate(List<Product> products) {
        // 创建计算上下文
        CalculationContext context = new CalculationContext(products, marketings, coupons, shippingFee);
        // 通过责任链计算最终结果
        return calculatorChain.calculate(products, context);
    }

}
