package org.dromara.merchant.domain.commodity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.common.core.utils.SnowflakeIdGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Description 商品spu
 * @Author Code Skywalker
 * @Date 2025/12/9 14:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spu {

    /**
     * 主键
     */
    private Long id = SnowflakeIdGenerator.generateId();

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
     * 一级分类
     */
    private Integer category1Id;

    /**
     * 二级分类
     */
    private Integer category2Id;

    /**
     * 三级分类
     */
    private Integer category3Id;

    /**
     * 模板ID
     */
    private Integer templateId;

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
     * 售后服务
     */
    private String saleService;

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
     * 定时上架时间
     */
    private LocalDateTime marketableTime;

    /**
     * 定时下架
     */
    private LocalDateTime unmarketableTime;

    /**
     * 是否上架,0已下架，1已上架
     */
    private Integer isMarketable;

    /**
     * 是否启用规格
     */
    private Integer isEnableSpec;

    /**
     * 是否删除,0:未删除，1：已删除
     */
    private Integer isDelete;

    /**
     * 审核状态，0：未审核，1：已审核，2：审核不通过
     */
    private Integer status;

    /**
     * 商户ID
     */
    private Long merchantId;

    /**
     * 租户ID
     */
    private String tenantId;

}
