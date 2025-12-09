package org.dromara.merchant.infrastructure.commodity.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.commodity.gateway.ISpuGateway;
import org.dromara.merchant.domain.commodity.model.Spu;
import org.dromara.merchant.infrastructure.commodity.converter.SpuConvertor;
import org.dromara.merchant.infrastructure.commodity.mapper.SpuMapper;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.SpuDO;
import org.springframework.stereotype.Component;

/**
 * @Description 商品spu网关
 * @Author Code Skywalker
 * @Date 2025/12/9 14:31
 */
@Component
@RequiredArgsConstructor
public class SpuGateway implements ISpuGateway {

    private final SpuMapper spuMapper;
    private final SpuConvertor spuConvertor;

    @Override
    public boolean save(Spu spu) {
        return this.spuMapper.insertOrUpdate(spuConvertor.toDo(spu));
    }

    @Override
    public boolean deleteById(Long id) {
        return this.spuMapper.deleteById(id) > 0;
    }

    @Override
    public Spu findById(Long id) {
        SpuDO spuDO = this.spuMapper.selectById(id);
        return this.spuConvertor.toEntity(spuDO);
    }
}
