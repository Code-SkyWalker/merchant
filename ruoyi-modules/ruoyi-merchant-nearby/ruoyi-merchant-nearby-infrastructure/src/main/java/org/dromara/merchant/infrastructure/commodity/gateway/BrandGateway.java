package org.dromara.merchant.infrastructure.commodity.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.commodity.gateway.IBrandGateway;
import org.dromara.merchant.domain.commodity.model.Brand;
import org.dromara.merchant.infrastructure.commodity.converter.BrandConvertor;
import org.dromara.merchant.infrastructure.commodity.mapper.BrandMapper;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.BrandDO;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandGateway implements IBrandGateway {

    private final BrandMapper mapper;
    private final BrandConvertor convertor;

    @Override
    public Integer save(Brand brand) {
        BrandDO brandDO = convertor.toDo(brand);
        boolean inserted = mapper.insertOrUpdate(brandDO);
        if (!inserted) return null;
        return brandDO.getId();
    }

    @Override
    public boolean delete(Integer id) {
        return mapper.deleteById(id) > 0;
    }

    @Override
    public Brand queryById(Integer id) {
        BrandDO brandDO = mapper.selectById(id);
        return convertor.toEntity(brandDO);
    }
}
