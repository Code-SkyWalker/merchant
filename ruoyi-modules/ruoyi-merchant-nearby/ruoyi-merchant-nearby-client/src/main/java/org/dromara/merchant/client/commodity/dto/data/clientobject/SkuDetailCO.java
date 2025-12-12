package org.dromara.merchant.client.commodity.dto.data.clientobject;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/11 16:31
 */
@Data
public class SkuDetailCO {

    /**
     * 商品id
     */
    private Long id;

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
     * 商品状态 1-正常，2-下架，3-删除
     */
    private Integer status;

    /**
     * 商品分类id
     */
    private Integer categoryId;

    /**
     * 商品分类名称
     */
    private String categoryName;

}
