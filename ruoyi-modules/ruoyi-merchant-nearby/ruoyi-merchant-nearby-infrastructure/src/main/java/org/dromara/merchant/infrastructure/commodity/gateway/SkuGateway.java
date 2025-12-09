package org.dromara.merchant.infrastructure.commodity.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.commodity.gateway.ISkuGateway;
import org.dromara.merchant.domain.commodity.model.Sku;
import org.dromara.merchant.infrastructure.commodity.converter.SkuConvertor;
import org.dromara.merchant.infrastructure.commodity.mapper.SkuMapper;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.SkuDO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 商品sku网关
 * @Author Code Skywalker
 * @Date 2025/12/9 17:07
 */
@Component
@RequiredArgsConstructor
public class SkuGateway implements ISkuGateway {

    private final SkuMapper skuMapper;
    private final SkuConvertor skuConvertor;

    @Override
    public boolean save(List<Sku> sku) {
        return this.skuMapper.insertBatch(skuConvertor.toDoList(sku));
    }

    @Override
    public boolean update(List<Sku> sku) {
        return this.skuMapper.updateBatchById(skuConvertor.toDoList(sku));
    }

    @Override
    public boolean deleteBySpuId(Long spuId) {
        return this.skuMapper.deleteBySpuId(spuId) > 0;
    }

    @Override
    public Sku queryById(Long id) {
        SkuDO skuDO = this.skuMapper.selectById(id);
        return this.skuConvertor.toEntity(skuDO);
    }

    @Override
    public List<Sku> queryBySpuId(Long spuId) {
        List<SkuDO> skuDOList = this.skuMapper.selectBySpuId(spuId);
        return this.skuConvertor.toEntityList(skuDOList);
    }
}
