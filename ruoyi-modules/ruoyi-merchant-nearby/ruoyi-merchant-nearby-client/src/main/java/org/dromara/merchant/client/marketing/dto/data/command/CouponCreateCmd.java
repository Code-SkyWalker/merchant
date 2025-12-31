package org.dromara.merchant.client.marketing.dto.data.command;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description 优惠券创建命令
 * @Author Code Skywalker
 * @Date 2025/12/29 11:19
 */
@Data
public class CouponCreateCmd {

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
    private String actuatingRange;

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
    private String goodsRange;

    /**
     * 商家Id
     */
    private Long merchantId;

    /**
     * 优惠券与商品关联 列表
     */
    List<CouponSpu> couponSpusCmds;

}
