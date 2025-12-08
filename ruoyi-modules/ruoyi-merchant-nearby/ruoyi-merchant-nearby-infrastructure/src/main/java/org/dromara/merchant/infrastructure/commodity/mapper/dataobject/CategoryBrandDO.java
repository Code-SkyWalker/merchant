package org.dromara.merchant.infrastructure.commodity.mapper.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;

/**
 * 分类品牌关系表
 */
@Data
@TableName(value = "tb_category_brand")
public class CategoryBrandDO {
    /**
     * 分类ID
     */
    @MppMultiId(value = "category_id")
    private Integer categoryId;

    /**
     * 品牌ID
     */
    @MppMultiId(value = "brand_id")
    private Integer brandId;
}