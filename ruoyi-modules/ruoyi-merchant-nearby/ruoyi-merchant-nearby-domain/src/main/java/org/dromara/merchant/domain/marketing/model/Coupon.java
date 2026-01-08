package org.dromara.merchant.domain.marketing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.common.core.utils.SnowflakeIdGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description 优惠券领域对象
 * @Author Code Skywalker
 * @Date 2025/12/29 10:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {

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
     * 是否可用
     */
    public boolean isApplicable() {
        LocalDateTime now = LocalDateTime.now();
        return this.serviceBegin.isBefore(now) && this.serviceEnd.isAfter(now)
            && !CouponType.OFFLINE.equals(this.actuatingRange);
    }

}
