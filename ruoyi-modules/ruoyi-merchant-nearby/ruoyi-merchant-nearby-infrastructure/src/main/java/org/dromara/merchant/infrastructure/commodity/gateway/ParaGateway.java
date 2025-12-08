package org.dromara.merchant.infrastructure.commodity.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.commodity.gateway.IParaGateway;
import org.dromara.merchant.domain.commodity.model.Para;
import org.dromara.merchant.infrastructure.commodity.converter.ParaConvertor;
import org.dromara.merchant.infrastructure.commodity.mapper.ParaMapper;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.ParaDO;
import org.springframework.stereotype.Component;

/**
 * @Description 商品参数模板网关接口
 * @Author Code Skywalker
 * @Date 2025/12/8 14:00
 */
@Component
@RequiredArgsConstructor
public class ParaGateway implements IParaGateway {

    private final ParaMapper paraMapper;
    private final ParaConvertor paraConvertor;

    @Override
    public boolean save(Para para) {
        return this.paraMapper.insertOrUpdate(paraConvertor.toDo(para));
    }

    @Override
    public boolean delete(Integer id) {
        return this.paraMapper.deleteById(id) > 0;
    }

    @Override
    public Para queryById(Integer id) {
        ParaDO paraDO = this.paraMapper.selectById(id);
        return this.paraConvertor.toEntity(paraDO);
    }
}
