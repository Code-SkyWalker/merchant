package org.dromara.merchant.app.order.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.commodity.ISkuService;
import org.dromara.merchant.app.marketing.IPricingCalculateService;
import org.dromara.merchant.app.merchant.IMerchantService;
import org.dromara.merchant.client.order.dto.data.command.OrderCreateCmd;
import org.dromara.merchant.domain.marketing.discount.CalculationResult;
import org.dromara.merchant.domain.marketing.discount.Product;
import org.dromara.merchant.domain.merchant.model.Merchant;
import org.dromara.merchant.domain.order.domainservice.IOrderDomainService;
import org.dromara.merchant.domain.order.model.Order;
import org.dromara.merchant.domain.order.model.OrderItem;
import org.dromara.merchant.domain.order.model.OrderSource;
import org.dromara.merchant.domain.order.model.OrderType;
import org.dromara.system.domain.vo.SysUserVo;
import org.dromara.system.service.ISysUserService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description 订单创建执行器
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Component
@RequiredArgsConstructor
public class OrderCreateExecutor {

    // 用户服务
    private final ISysUserService sysUserService;

    // 商家服务
    private final IMerchantService merchantService;

    // 价格计算服务
    private final IPricingCalculateService pricingCalculateService;

    // 订单领域服务
    private final IOrderDomainService orderDomainService;

    // sku服务
    private final ISkuService skuService;

    public Long execute(OrderCreateCmd cmd) {

        SysUserVo user = queryUser(cmd.getUserId());
        Merchant merchant = queryMerchant(cmd.getMerchantId());

        // 设置用户和商户信息
        Order order = new Order();
        order.setUserId(user.getUserId());
        order.setUserName(user.getUserName());
        order.setMerchantId(merchant.getMerchantId());
        order.setMerchantName(merchant.getMerchantName());

        // 设置订单类型和来源
        order.setType(OrderType.NORMAL);
        order.setSource(OrderSource.APP);

        // 设置积分和佣金抵扣金额
        order.setPointAmount(cmd.getIntegralDiscountAmount());
        order.setCommAmount(cmd.getCommDiscountAmount());

        // 计算商品最终价格
        CalculationResult result = this.pricingCalculateService.calculateFinalPrice(cmd.toPriceCalculationCmd());

        // 设置订单价格信息
        result.updateOrder(order);

        // 构建订单商品
        order.setOrderItems(this.buildOrderItems(result.getProducts()));

        return orderDomainService.createOrder(order);
    }

    /**
     * 查询用户
     */
    private SysUserVo queryUser(Long userId) {
        return this.sysUserService.selectUserById(userId);
    }

    /**
     * 查询商家
     */
    private Merchant queryMerchant(Long merchantId) {
        return this.merchantService.queryById(merchantId);
    }

    /**
     * 构建订单商品
     *
     * @param resultMap 商品列表
     * @return 订单商品列表
     */
    private List<OrderItem> buildOrderItems(Map<Product, BigDecimal> resultMap) {
        if (resultMap == null || resultMap.isEmpty()) return Collections.emptyList();

        // 计算每个商品的折扣金额
        Map<Long, BigDecimal> totalDiscounts = new HashMap<>();
        // 记录每个商品的最终价格
        Map<Long, BigDecimal> finalPrices = new HashMap<>();

        // 遍历所有商品，计算折扣和最终价格
        for (Product product : resultMap.keySet()) {
            BigDecimal original = product.getTotalPrice();  // 原始价格
            BigDecimal current = resultMap.get(product).max(BigDecimal.ZERO);  // 当前价格，不能为负数
            finalPrices.put(product.getSkuId(), current);  // 设置最终价格
            totalDiscounts.put(product.getSkuId(), original.subtract(current));  // 计算折扣金额
        }

        Map<Long, Integer> quantity = resultMap.keySet().stream().collect(Collectors.toMap(Product::getSkuId, Product::getQuantity));

        return this.skuService.queryBySkuIds(quantity.keySet()).stream().map(
            sku -> new OrderItem()
                .setSpuId(sku.getSpuId())
                .setSkuId(sku.getId())
                .setSkuName(sku.getName())
                .setSkuPic(sku.getImage())
                .setSkuSpec(sku.getSpec())
                .setUnitPrice(sku.getPrice())
                .setQuantity(quantity.get(sku.getId()))
                .setSubtotal(finalPrices.get(sku.getId()))
                .setDiscountAmount(totalDiscounts.get(sku.getId()))
                .setFinalAmount(finalPrices.get(sku.getId()))
                .setCommissionRate(BigDecimal.ZERO)
                .setCommissionAmount(BigDecimal.ZERO)
                .setPostFee(BigDecimal.ZERO)
        ).toList();
    }
}
