package org.dromara.merchant.app.marketing;

import org.dromara.merchant.client.marketing.dto.data.command.PriceCalculationCmd;
import org.dromara.merchant.domain.marketing.discount.CalculationResult;
import org.dromara.merchant.domain.marketing.discount.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description 价格计算服务接口
 * @Author Code Skywalker
 * @Date 2026/1/16 13:30
 */
public interface IPricingCalculateService {

    /**
     * 计算配送费
     *
     * @param products   商品列表
     * @param merchantId 商家id
     * @param addressId  地址id
     * @param subtotal   支付价小计
     * @param withinRange 是否在配送范围内
     * @return 配送费
     */
    BigDecimal calculateExpressFee(List<Product> products, Long merchantId, Long addressId, BigDecimal subtotal, boolean withinRange);

    /**
     * 计算商品最终价格
     *
     * @param cmd 计算价格命令
     * @return 计算结果
     */
    CalculationResult calculateFinalPrice(PriceCalculationCmd cmd);
}
