package org.dromara.merchant.app.order.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.commodity.ISkuService;
import org.dromara.merchant.app.marketing.IPricingCalculateService;
import org.dromara.merchant.app.merchant.IMerchantService;
import org.dromara.merchant.client.order.dto.data.command.OrderCancelCmd;
import org.dromara.merchant.client.order.dto.data.command.OrderCreateCmd;
import org.dromara.merchant.client.order.dto.data.command.OrderPayCmd;
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
 * @Description 订单取消执行器
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Component
@RequiredArgsConstructor
public class OrderCancelExecutor {

    private final IOrderDomainService orderDomainService;

    public Boolean execute(OrderCancelCmd cmd) {
        return orderDomainService.cancelOrder(cmd.getOrderId(), cmd.getCancelReason());
    }
}
