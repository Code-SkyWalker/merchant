package org.dromara.merchant.domain.category.gateway;


import org.dromara.merchant.domain.category.model.Category;

/**
 * @Description 分类网关接口
 * @Author Code Skywalker
 * @Date 2025-10-23 11:36
 */
public interface ICategoryGateway {

    /**
     * 创建分类
     *
     * @param category 分类
     * @return 是否成功
     */
    boolean create(Category category);

    /**
     * 修改分类
     *
     * @param category 分类
     * @return 是否成功
     */
    boolean modify(Category category);

    /**
     * 删除分类
     *
     * @param categoryId 分类ID
     * @return 是否成功
     */
    boolean delete(Long categoryId);

    /**
     * 根据ID查询分类
     *
     * @param categoryId 分类ID
     * @return 分类
     */
    Category selectById(Long categoryId);

}
