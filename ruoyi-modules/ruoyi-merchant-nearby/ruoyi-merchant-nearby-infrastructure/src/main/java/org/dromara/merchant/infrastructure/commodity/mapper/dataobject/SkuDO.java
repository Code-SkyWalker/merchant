package org.dromara.merchant.infrastructure.commodity.mapper.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 商品sku表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_sku")
public class SkuDO {
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
