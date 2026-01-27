package org.dromara.merchant.app.freight.executor.query;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.freight.dto.data.clientobject.ExpressAreaCO;
import org.dromara.merchant.infrastructure.freight.converter.ExpressAreaConvertor;
import org.dromara.merchant.infrastructure.freight.mapper.dataobject.ExpressAreaDO;
import org.dromara.merchant.infrastructure.freight.mapper.ExpressAreaMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 商户配送区域列表查询执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
@Component
@RequiredArgsConstructor
public class ExpressAreaListQryExecutor {

    private final ExpressAreaMapper mapper;
    private final ExpressAreaConvertor convertor;

    public List<ExpressAreaCO> execute(Long templateId) {
        List<ExpressAreaDO> areas = mapper.selectByTemplateId(templateId);
        return this.convertor.toListCO(areas);
    }

}
