package org.dromara.merchant.client.commodity.dto.data.command;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description 商品sku创建参数
 * @Author Code Skywalker
 * @Date 2025/12/9 17:14
 */
@Data
public class SkuCreateCmd {

    /**
     * 商品条码
     */
    private String sn;

    /**
     * SKU名称
     */
    private String name;

    /**
     * 价格
     */
    @NotNull
    private BigDecimal price;

    /**
     * 普通会员价
     */
    private BigDecimal memberPrice;

    /**
     * 超级会员价
     */
    private BigDecimal vipPrice;

    /**
     * 划线价
     */
    private BigDecimal originalPrice;

    /**
     * 库存数量
     */
    @NotNull
    private Integer num = 1;

    /**
     * 库存预警数量
     */
    private Integer alertNum;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 商品图片列表
     */
    private String images;

    /**
     * 重量（千克）
     */
    private BigDecimal weight = BigDecimal.ONE;

    /**
     * 体积（立方）
     */
    private BigDecimal volume = new BigDecimal("0.01");

    /**
     * 起购量
     */
    private Integer minPurchase = 1;

    /**
     * 限购量
     */
    private Integer maxPurchase;

    /**
     * SPUID
     */
    private Long spuId;

    /**
     * 类目ID
     */
    @NotNull
    private Integer categoryId;

    /**
     * 类目名称
     */
    @NotNull
    private String categoryName;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 规格
     */
    private String spec;

}
