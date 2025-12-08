package org.dromara.merchant.infrastructure.commodity.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.commodity.gateway.ISpecGateway;
import org.dromara.merchant.domain.commodity.model.Spec;
import org.dromara.merchant.infrastructure.commodity.converter.SpecConvertor;
import org.dromara.merchant.infrastructure.commodity.mapper.SpecMapper;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.SpecDO;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/8 13:44
 */
@Component
@RequiredArgsConstructor
public class SpecGateway implements ISpecGateway {

    private final SpecMapper specMapper;
    private final SpecConvertor convertor;

    @Override
    public boolean save(Spec spec) {
        return this.specMapper.insertOrUpdate(convertor.toDo(spec));
    }


    @Override
    public boolean delete(Integer id) {
        return this.specMapper.deleteById(id) > 0;
    }

    @Override
    public Spec queryById(Integer id) {
        SpecDO specDO = this.specMapper.selectById(id);
        return convertor.toEntity(specDO);
    }
}
