package org.dromara.merchant.app.commodity;

import org.dromara.merchant.client.commodity.dto.data.command.SkuCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.SkuModifyCmd;
import org.dromara.merchant.domain.commodity.model.Sku;

import java.util.List;

/**
 * @Description 商品SKU服务接口
 * @Author Code Skywalker
 * @Date 2025/12/9 17:26
 */
public interface ISkuService {

    /**
     * 批量创建商品sku
     * @param cmd 创建参数
     * @return 创建结果
     */
    boolean create(List<SkuCreateCmd> cmd);

    /**
     * 批量修改商品sku
     * @param cmd 修改参数
     * @return 修改结果
     */
    boolean modify(List<SkuModifyCmd> cmd);

    /**
     * 根据spuId删除商品sku
     * @param spuId spuId
     * @return 删除结果
     */
    boolean deleteBySpuId(Long spuId);

    /**
     * 根据id查询商品sku
     * @param id 商品skuid
     * @return 商品sku
     */
    Sku queryById(Long id);

    /**
     * 根据spuId查询商品sku
     * @param spuId spuId
     * @return 商品sku
     */
    List<Sku> queryBySpuId(Long spuId);
}
