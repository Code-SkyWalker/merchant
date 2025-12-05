package org.dromara.merchant.client.freight.dto.data.command;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;

import java.math.BigDecimal;

/**
 * @Description 商户配送区域创建命令
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
@Data
public class ExpressAreaCreateCmd {

    /**
     * 运费模板ID
     */
    @NotNull(message = "运费模板ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long templateId;

    /**
     * 区划代码集合
     */
    private String adCode;

    /**
     * 首重/首件/首体积
     */
    @NotNull(message = "首重/首件/首体积不能为空", groups = {AddGroup.class, EditGroup.class})
    private BigDecimal baseWeightQuantityVolume;

    /**
     * 首费
     */
    @NotNull(message = "首费不能为空", groups = {AddGroup.class, EditGroup.class})
    private BigDecimal baseFee;

    /**
     * 续重/续件/续体积
     */
    @NotNull(message = "续重/续件/续体积不能为空", groups = {AddGroup.class, EditGroup.class})
    private BigDecimal additionalWeightQuantityVolume;

    /**
     * 续费
     */
    @NotNull(message = "续费不能为空", groups = {AddGroup.class, EditGroup.class})
    private BigDecimal additionalFee;

    /**
     * 排序
     */
    private Integer sort = 0;
}
