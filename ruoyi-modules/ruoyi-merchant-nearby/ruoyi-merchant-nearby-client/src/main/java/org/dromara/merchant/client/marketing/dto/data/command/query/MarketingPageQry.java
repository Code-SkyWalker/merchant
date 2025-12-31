package org.dromara.merchant.client.marketing.dto.data.command.query;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Description 营销活动分页查询参数
 * @Author Code Skywalker
 * @Date 2025/12/30 13:35
 */
@Data
public class MarketingPageQry {

    /**
     * 营销活动名称
     */
    private String name;

    /**
     * 营销活动类型: QUANTITY:x件x折, MULTIUNIT:满折满减, BULK:n元n件
     */
    private String type;

    /**
     * 商家Id
     */
    @NotNull
    private Long merchantId;

}
