package org.dromara.merchant.domain.merchant.gateway;


import org.dromara.merchant.domain.merchant.model.MerCategoryDefault;

/**
 * @Description 默认分类网关接口
 * @Author Code Skywalker
 * @Date 2025-10-23 11:36
 */
public interface IMerCategoryDefaultGateway {

    /**
     * 创建分类
     *
     * @param merCategoryDefault 分类
     * @return 是否成功
     */
    boolean create(MerCategoryDefault merCategoryDefault);

    /**
     * 修改分类
     *
     * @param merCategoryDefault 分类
     * @return 是否成功
     */
    boolean modify(MerCategoryDefault merCategoryDefault);

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
    MerCategoryDefault selectById(Long categoryId);

    /**
     * 写入默认分类
     * @return
     */
    boolean writeDefault();

}
