package org.dromara.merchant.domain.commodity.gateway;

import org.dromara.merchant.domain.commodity.model.Para;

/**
 * @Description 商品参数模板网关接口
 * @Author Code Skywalker
 * @Date 2025/12/8 13:58
 */
public interface IParaGateway {

    /**
     * 保存商品参数模板
     * @param para 商品参数
     * @return 是否保存成功
     */
    boolean save(Para para);

    /**
     * 删除商品参数模板
     * @param id 商品参数id
     * @return 是否删除成功
     */
    boolean delete(Integer id);

    /**
     * 根据id查询商品参数模板
     * @param id 商品参数id
     * @return 商品参数
     */
    Para queryById(Integer id);
}
