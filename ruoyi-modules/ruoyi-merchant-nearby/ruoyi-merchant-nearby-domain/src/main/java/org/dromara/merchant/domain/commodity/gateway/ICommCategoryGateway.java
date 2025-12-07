package org.dromara.merchant.domain.commodity.gateway;

import org.dromara.merchant.domain.commodity.model.CommCategory;

import java.util.List;

/**
 * @Description 商品网关接口
 * @Author Code Skywalker
 * @Date 2025-10-23 11:37
 */
public interface ICommCategoryGateway {
    /**
     * 保存商品类目
     * @param commCategory 类目
     * @return 是否保存成功
     */
    boolean save(CommCategory commCategory);

    /**
     * 删除商品类目
     * @param id 类目ID
     * @return 是否删除成功
     */
    boolean delete(Integer id);

    /**
     * 根据ID查询商品类目
     * @param id 类目ID
     * @return 类目
     */
    CommCategory queryById(Integer id);

    /**
     * 根据父类目ID查询子类目
     * @param parentId 父类目ID
     * @return 子类目列表
     */
    List<CommCategory> queryByParentId(Integer parentId);
}
