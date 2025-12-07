package org.dromara.merchant.infrastructure.commodity.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.commodity.gateway.ICommCategoryGateway;
import org.dromara.merchant.domain.commodity.model.CommCategory;
import org.dromara.merchant.infrastructure.commodity.converter.CommCategoryConvertor;
import org.dromara.merchant.infrastructure.commodity.mapper.CommCategoryMapper;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.CategoryDO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommCategoryGateway implements ICommCategoryGateway {

    private final CommCategoryMapper mapper;
    private final CommCategoryConvertor convertor;

    @Override
    public boolean save(CommCategory commCategory) {
        return mapper.insertOrUpdate(convertor.toDO(commCategory));
    }

    @Override
    public boolean delete(Integer id) {
        return mapper.deleteById(id) > 0;
    }

    @Override
    public CommCategory queryById(Integer id) {
        CategoryDO categoryDO = mapper.selectById(id);
        return convertor.toEntity(categoryDO);
    }

    @Override
    public List<CommCategory> queryByParentId(Integer parentId) {
        return convertor.toEntityList(mapper.selectByParentId(parentId));
    }
}
