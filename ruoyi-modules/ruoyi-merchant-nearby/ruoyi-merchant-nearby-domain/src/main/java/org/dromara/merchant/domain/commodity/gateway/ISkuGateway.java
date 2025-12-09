package org.dromara.merchant.domain.commodity.gateway;

import org.dromara.merchant.domain.commodity.model.Sku;

import java.util.List;

/**
 * @Description 商品sku网关
 * @Author Code Skywalker
 * @Date 2025/12/9 17:05
 */
public interface ISkuGateway {

    /**
     * 保存
     * @param sku 商品sku
     * @return 保存结果
     */
    boolean save(List<Sku> sku);

    /**
     * 修改
     * @param sku 待修改商品sku
     * @return 修改结果
     */
    boolean update(List<Sku> sku);

    /**
     * 根据spuId删除
     * @param spuId spuId
     * @return 删除结果
     */
    boolean deleteBySpuId(Long spuId);

    /**
     * 查询
     * @param id 商品skuid
     * @return 商品sku
     */
    Sku queryById(Long id);

    /**
     * 根据spuId查询
     * @param spuId spuId
     * @return 商品sku列表
     */
    List<Sku> queryBySpuId(Long spuId);

}
