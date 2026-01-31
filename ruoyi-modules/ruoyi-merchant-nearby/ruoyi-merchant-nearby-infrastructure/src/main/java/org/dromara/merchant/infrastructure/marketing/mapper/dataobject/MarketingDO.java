package org.dromara.merchant.infrastructure.marketing.mapper.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.time.LocalDateTime;

/**
 * 营销活动
 */
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_marketing")
public class MarketingDO extends BaseEntity {
    /**
     * 活动主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动时间（开始）
     */
    private LocalDateTime receiveBegin;

    /**
     * 活动时间（结束）
     */
    private LocalDateTime receiveEnd;

    /**
     * 活动规则
     */
    private String rules;

    /**
     * 活动类型：QUANTITY:x件x折, MULTIUNIT:满折满减 BULK:n元n件
     */
    private String type;

    /**
     * 商家Id
     */
    private Long merchantId;

    /**
     * 租户编号
     */
    private String tenantId;
}
