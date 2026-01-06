package org.dromara.merchant.domain.order.marketing;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.commodity.model.Sku;
import org.dromara.merchant.domain.marketing.gateway.IMarketingGateway;
import org.dromara.merchant.domain.marketing.model.Marketing;
import org.dromara.merchant.domain.marketing.service.PriceCalculationStrategy;
import org.dromara.merchant.domain.order.model.Order;
import org.dromara.merchant.domain.order.model.OrderItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 营销活动计算服务
 * @Author 订单体系改进
 * @Date 2026-01-05
 */
@Component
@RequiredArgsConstructor
public class MarketingCalculationService {

    private final PriceCalculationStrategy bestDiscountStrategy;

    /**
     * 计算订单优惠金额（基于营销活动）
     *
     * @param order 订单
     * @return 优惠金额
     */
    public BigDecimal calculateMarketingDiscount(Order order) {
        BigDecimal totalDiscount = BigDecimal.ZERO;

        // 计算营销活动优惠
        if (order.getOrderItems() != null && !order.getOrderItems().isEmpty()) {
            for (OrderItem item : order.getOrderItems()) {

                // 使用项目中的营销活动计算策略
                Sku sku = new Sku();
                sku.setId(item.getSkuId());
                sku.setSpuId(item.getSpuId());
                sku.setPrice(item.getUnitPrice());

                // 计算该订单项的最终价格
                BigDecimal finalPrice = bestDiscountStrategy.calculateFinalPrice(sku, item.getQuantity());

                // 计算优惠金额：原价 - 优惠后价格
                BigDecimal originalPrice = item.getUnitPrice().multiply(new BigDecimal(item.getQuantity()));
                BigDecimal itemDiscount = originalPrice.subtract(finalPrice);

                totalDiscount = totalDiscount.add(itemDiscount);
            }
        }

        return totalDiscount;
    }


    /**
     * 计算优惠券抵扣金额
     *
     * @param order 订单
     * @return 优惠券抵扣金额
     */
    public BigDecimal calculateCouponDiscount(Order order) {
        // 这里应该根据订单信息和优惠券规则计算优惠券抵扣金额
        // 暂时返回订单中已设置的优惠券金额
        return order.getCouponAmount() != null ? order.getCouponAmount() : BigDecimal.ZERO;
    }

    /**
     * 计算积分抵扣金额
     *
     * @param order 订单
     * @return 积分抵扣金额
     */
    public BigDecimal calculatePointDiscount(Order order) {
        // 这里应该根据订单信息和积分规则计算积分抵扣金额
        // 暂时返回订单中已设置的积分金额
        return order.getPointAmount() != null ? order.getPointAmount() : BigDecimal.ZERO;
    }

    /**
     * 综合计算订单总优惠金额
     *
     * @param order 订单
     * @return 总优惠金额
     */
    public BigDecimal calculateTotalDiscount(Order order) {
        BigDecimal marketingDiscount = calculateMarketingDiscount(order);
        BigDecimal couponDiscount = calculateCouponDiscount(order);
        BigDecimal pointDiscount = calculatePointDiscount(order);

        return marketingDiscount.add(couponDiscount).add(pointDiscount);
    }

    /**
     * 计算订单应付金额
     *
     * @param order 订单
     * @return 应付金额
     */
    public BigDecimal calculatePayableAmount(Order order) {
        BigDecimal goodsAmount = order.getGoodsAmount() != null ? order.getGoodsAmount() : BigDecimal.ZERO;
        BigDecimal freightAmount = order.getFreightAmount() != null ? order.getFreightAmount() : BigDecimal.ZERO;
        BigDecimal totalDiscount = calculateTotalDiscount(order);

        BigDecimal payableAmount = goodsAmount.add(freightAmount).subtract(totalDiscount);
        return payableAmount.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : payableAmount;
    }
}
