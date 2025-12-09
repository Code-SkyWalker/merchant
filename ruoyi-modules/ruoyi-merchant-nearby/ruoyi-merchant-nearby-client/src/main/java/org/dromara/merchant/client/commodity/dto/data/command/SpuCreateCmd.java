package org.dromara.merchant.client.commodity.dto.data.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description 商品spu创建参数
 * @Author Code Skywalker
 * @Date 2025/12/9 14:35
 */
@Data
public class SpuCreateCmd {

    /**
     * 货号
     */
    private String sn;

    /**
     * SPU名
     */
    @NotBlank
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
     * 一级分类
     */
    @NotBlank
    private Integer category1Id;

    /**
     * 二级分类
     */
    @NotBlank
    private Integer category2Id;

    /**
     * 三级分类
     */
    @NotBlank
    private Integer category3Id;

    /**
     * 模板ID
     */
    @NotBlank
    private Integer templateId;

    /**
     * 运费模板id
     */
    @NotBlank
    private String freightId;

    /**
     * 配送方式：0无需配送 1需要配送
     */
    @NotBlank
    private Integer freightType;

    /**
     * 图片
     */
    @NotBlank
    private String image;

    /**
     * 图片列表
     */
    @NotBlank
    private String images;

    /**
     * 视频
     */
    private String video;

    /**
     * 售后服务
     */
    private String saleService;

    /**
     * 介绍
     */
    @NotBlank
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
     * 商品类型
     */
    @NotBlank
    private String type;

    /**
     * 佣金
     */
    private BigDecimal commition;

    /**
     * 商品能否加入购物车 0否 1是 默认1
     */
    @NotBlank
    private Integer cart;

    /**
     * 库存单位
     */
    @NotBlank
    private String stockUnit;

    /**
     * 上架类型：0立即上架 1暂不上架 2定时上架
     */
    @NotBlank
    private Integer marketableMode;

    /**
     * 定时上架时间
     */
    private LocalDateTime marketableTime;

    /**
     * 定时下架
     */
    private LocalDateTime unmarketableTime;

    /**
     * 是否启用规格
     */
    @NotBlank
    private Integer isEnableSpec;

    /**
     * 商户ID
     */
    @NotBlank
    private Long merchantId;

    /**
     * 商品sku列表
     */
    List<SkuCreateCmd> skus;

}
