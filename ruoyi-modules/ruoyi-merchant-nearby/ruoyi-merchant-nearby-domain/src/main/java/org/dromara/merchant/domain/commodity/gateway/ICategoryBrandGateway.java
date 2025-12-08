package org.dromara.merchant.domain.commodity.gateway;

import org.dromara.merchant.domain.commodity.model.CategoryBrand;

import java.util.List;

public interface ICategoryBrandGateway {

    /**
     * 批量保存
     * @param categoryBrands 商品分类关系
     * @return 是否保存成功
     */
    boolean save(CategoryBrand categoryBrands);

    /**
     * 批量保存
     * @param categoryBrands 批量商品分类关系
     * @return 是否保存成功
     */
    boolean saveBatch(List<CategoryBrand> categoryBrands);

    /**
     * 根据分类id和品牌id删除
     * @param categoryBrand 商品分类关系
     * @return 是否删除成功
     */
    boolean deleteByCategoryIdAndBrandId(CategoryBrand categoryBrand);

}
