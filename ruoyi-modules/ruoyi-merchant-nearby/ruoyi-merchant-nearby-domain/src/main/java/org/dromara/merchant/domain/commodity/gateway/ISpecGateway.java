package org.dromara.merchant.domain.commodity.gateway;

import org.dromara.merchant.domain.commodity.model.Spec;

/**
 * @Description 商品规格网关接口
 * @Author Code Skywalker
 * @Date 2025/12/8 13:43
 */
public interface ISpecGateway {

    /**
     * 保存商品规格
     * @param spec 商品规格
     * @return 是否保存成功
     */
    boolean save(Spec spec);

    /**
     * 删除商品规格
     * @param id 商品规格id
     * @return 是否删除成功
     */
    boolean delete(Integer id);

    /**
     * 查询商品规格
     * @param id 商品规格id
     * @return 商品规格
     */
    Spec queryById(Integer id);
}
