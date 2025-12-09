package org.dromara.merchant.domain.commodity.gateway;

import org.dromara.merchant.domain.commodity.model.Spu;

/**
 * @Description 商品spu网关
 * @Author Code Skywalker
 * @Date 2025/12/9 14:30
 */
public interface ISpuGateway {

    /**
     * 保存spu
     * @param spu 商品spu
     * @return 保存结果
     */
    boolean save(Spu spu);

    /**
     * 删除spu
     * @param id spu id
     * @return 删除结果
     */
    boolean deleteById(Long id);

    /**
     * 根据id查询spu
     * @param id spu id
     * @return spu
     */
    Spu findById(Long id);

}
