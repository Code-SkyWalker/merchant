package org.dromara.huifu.infrastructure.mapper.dataobject;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.tenant.core.TenantEntity;

import java.math.BigDecimal;

/**
 * 汇付分账比例
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("tb_payment_huifu_fee_rate")
public class HuifuFeeRateDO extends BaseEntity {

    /**
     * 分账比例ID
     */
    @TableId(type = IdType.INPUT)
    private Long rateId;

    /**
    * 分账比例（百分比值）
    */
    private BigDecimal feeRate;

    /**
     * 比例类别：0：普通商家 1：运营平台（租户） 2：汇付渠道商（益巨科技）
     */
    private Integer type;

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 备注
     */
    private String note;
}
