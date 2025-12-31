package org.dromara.merchant.client.marketing.dto.data.command.query;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Description 优惠券查询参数
 * @Author Code Skywalker
 * @Date 2025/12/30 10:28
 */
@Data
public class CouponPageQry {

    /**
     * 优惠券名称
     */
    private String couponName;

    /**
     * 商家Id
     */
    @NotNull
    private Long merchantId;
}
