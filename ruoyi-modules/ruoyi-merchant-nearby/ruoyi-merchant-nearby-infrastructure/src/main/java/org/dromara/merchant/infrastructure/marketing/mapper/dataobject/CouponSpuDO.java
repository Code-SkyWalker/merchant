package org.dromara.merchant.infrastructure.marketing.mapper.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 优惠券与商品关联表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_coupon_spu")
public class CouponSpuDO {
    /**
     * 关联主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 活动主键
     */
    private Long couponId;

    /**
     * spuid
     */
    private Long spuId;

    /**
     * 秒杀价格 仅秒杀活动使用
     */
    private BigDecimal killPrice;
}
