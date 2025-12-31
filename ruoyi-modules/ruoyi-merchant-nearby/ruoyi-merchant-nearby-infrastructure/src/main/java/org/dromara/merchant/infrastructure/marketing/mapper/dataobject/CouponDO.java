package org.dromara.merchant.infrastructure.marketing.mapper.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 优惠券
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_coupon")
public class CouponDO extends BaseEntity {

    /**
     * 优惠券主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

}
