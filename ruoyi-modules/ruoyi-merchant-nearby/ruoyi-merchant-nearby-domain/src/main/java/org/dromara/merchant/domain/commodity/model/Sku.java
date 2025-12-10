package org.dromara.merchant.domain.commodity.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.common.core.utils.SnowflakeIdGenerator;

import java.math.BigDecimal;

/**
 * @Description 商品sku
 * @Author Code Skywalker
 * @Date 2025/12/9 16:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sku {

    /**
     * 商品id
     */
    private Long id = SnowflakeIdGenerator.generateId();

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
    private Integer num;

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
    private BigDecimal weight;

    /**
     * 体积（立方）
     */
    private BigDecimal volume;

    /**
     * 起购量
     */
    private Integer minPurchase;

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
    private Integer categoryId;

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 规格
     */
    private String spec;

    /**
     * 销量
     */
    private Integer saleNum;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 商品状态 1-正常，2-下架，3-删除
     */
    private Integer status;

}
