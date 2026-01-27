package org.dromara.merchant.app.freight.executor.query;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.freight.dto.data.clientobject.ExpressAreaCO;
import org.dromara.merchant.infrastructure.freight.converter.ExpressAreaConvertor;
import org.dromara.merchant.infrastructure.freight.mapper.dataobject.ExpressAreaDO;
import org.dromara.merchant.infrastructure.freight.mapper.ExpressAreaMapper;
import org.springframework.stereotype.Component;

/**
 * @Description 商户配送区域详情查询执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
@Component
@RequiredArgsConstructor
public class ExpressAreaDetailQryExecutor {

    private final ExpressAreaMapper mapper;
    private final ExpressAreaConvertor convertor;

    public ExpressAreaCO execute(Long areaId) {
        ExpressAreaDO area = mapper.selectById(areaId);
        return this.convertor.toCO(area);
    }

}
