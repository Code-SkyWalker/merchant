package org.dromara.merchant.client.commodity.dto.data.clientobject;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description spu 详情
 * @Author Code Skywalker
 * @Date 2025/12/11 16:26
 */
@Data
public class SpuDetailCO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 商户Id
     */
    private Long merchantId;

    /**
     * 货号
     */
    private String sn;

    /**
     * SPU名
     */
    private String name;

    /**
     * 副标题
     */
    private String caption;

    /**
     * 品牌ID
     */
    private Integer brandId;

    /**
     * 三级分类
     */
    private Integer category3Id;

    /**
     * 运费模板id
     */
    private String freightId;

    /**
     * 配送方式：0无需配送 1需要配送
     */
    private Integer freightType;

    /**
     * 图片
     */
    private String image;

    /**
     * 图片列表
     */
    private String images;

    /**
     * 视频
     */
    private String video;

    /**
     * 介绍
     */
    private String introduction;

    /**
     * 规格列表
     */
    private String specItems;

    /**
     * 参数列表
     */
    private String paraItems;

    /**
     * 销量
     */
    private Integer saleNum;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 商品类型
     */
    private String type;

    /**
     * 佣金
     */
    private BigDecimal commition;

    /**
     * 商品能否加入购物车 0否 1是 默认1
     */
    private Integer cart;

    /**
     * 库存单位
     */
    private String stockUnit;

    /**
     * 上架类型：0立即上架 1暂不上架 2定时上架
     */
    private Integer marketableMode;

    /**
     * 是否启用规格
     */
    private Integer isEnableSpec;

    /**
     * 商品SKU列表
     */
    private List<SkuDetailCO> skus;

}
