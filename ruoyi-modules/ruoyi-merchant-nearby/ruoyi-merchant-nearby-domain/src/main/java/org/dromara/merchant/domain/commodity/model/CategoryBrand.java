package org.dromara.merchant.domain.commodity.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryBrand {

    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 品牌ID
     */
    private Integer brandId;
}
