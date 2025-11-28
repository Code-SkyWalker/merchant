package org.dromara.merchant.domain.category.gateway;


import org.dromara.merchant.domain.category.model.CategoryDefault;

/**
 * @Description 默认分类网关接口
 * @Author Code Skywalker
 * @Date 2025-10-23 11:36
 */
public interface ICategoryDefaultGateway {

    /**
     * 创建分类
     *
     * @param categoryDefault 分类
     * @return 是否成功
     */
    boolean create(CategoryDefault categoryDefault);

    /**
     * 修改分类
     *
     * @param categoryDefault 分类
     * @return 是否成功
     */
    boolean modify(CategoryDefault categoryDefault);

    /**
     * 删除分类
     *
     * @param categoryId 分类ID
     * @return 是否成功
     */
    boolean delete(Long categoryId);

    /**
     * 根据租户ID删除分类
     *
     * @param tenantId 租户ID
     * @return 是否成功
     */
    boolean deleteByTenantId(String tenantId);

    /**
     * 根据ID查询分类
     *
     * @param categoryId 分类ID
     * @return 分类
     */
    CategoryDefault selectById(Long categoryId);

    /**
     * 写入默认分类
     * @return
     */
    boolean writeDefault();

}
