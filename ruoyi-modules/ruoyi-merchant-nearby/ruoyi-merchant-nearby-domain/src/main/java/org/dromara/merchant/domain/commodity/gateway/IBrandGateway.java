package org.dromara.merchant.domain.commodity.gateway;

import org.dromara.merchant.domain.commodity.model.Brand;

/**
 * @Description 商品网关接口
 * @Author Code Skywalker
 * @Date 2025-10-23 11:37
 */
public interface IBrandGateway {

    /**
     * 保存商品品牌
     * @param brand 品牌
     * @return 是否保存成功
     */
    boolean save(Brand brand);

    /**
     * 删除商品品牌
     * @param id 品牌ID
     * @return 是否删除成功
     */
    boolean delete(Integer id);

    /**
     * 根据ID查询商品品牌
     * @param id 品牌ID
     * @return 品牌
     */
    Brand queryById(Integer id);


}
