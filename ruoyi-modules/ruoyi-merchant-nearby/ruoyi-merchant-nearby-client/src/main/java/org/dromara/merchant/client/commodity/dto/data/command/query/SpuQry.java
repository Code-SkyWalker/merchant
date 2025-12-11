package org.dromara.merchant.client.commodity.dto.data.command.query;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品spu查询参数
 * @Author Code Skywalker
 * @Date 2025/12/11 15:47
 */
@Data
public class SpuQry {

    /**
     * 商品名称
     */
    private String name;

    /**
     * 第三级类目ID
     */
    private Integer categoryId;

    /**
     * 价格范围
     */
    @Size(min = 2, max = 2, message = "价格范围输入不规范")
    private List<BigDecimal> priceRange;

    /**
     * 创建时间范围
     */
    @Size(min = 2, max = 2, message = "创建时间范围输入不规范")
    private List<LocalDateTime> createTimeRange;

    /**
     * 是否上架
     */
    private Boolean marketable;

    /**
     * 审核状态
     */
    private Integer status;

    /**
     * 商户ID
     */
    private Long merchantId;
}
