package org.dromara.merchant.domain.marketing.discount;

import java.math.BigDecimal;

/**
 * @Description 商城活动接口
 * @Author Code Skywalker
 * @Date 2026/1/8 16:35
 */
public interface Activities {

    /**
     * 是否可用
     * @return true/false
     */
    boolean isApplicable();

    /**
     * 计算优惠金额
     * @return 优惠的金额
     */
    BigDecimal calculateDiscount();

}
