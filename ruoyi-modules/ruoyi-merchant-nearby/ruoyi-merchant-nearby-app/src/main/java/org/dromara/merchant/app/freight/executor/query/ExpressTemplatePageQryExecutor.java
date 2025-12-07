package org.dromara.merchant.app.freight.executor.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.freight.dto.data.clientobject.ExpressTemplateCO;
import org.dromara.merchant.client.freight.dto.data.command.query.ExpressTemplatePageQry;
import org.dromara.merchant.infrastructure.freight.converter.ExpressTemplateConvertor;
import org.dromara.merchant.infrastructure.freight.gateway.dataobject.ExpressTemplateDO;
import org.dromara.merchant.infrastructure.freight.mapper.ExpressTemplateMapper;
import org.springframework.stereotype.Component;

/**
 * @Description 商户运费模板分页查询执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Component
@RequiredArgsConstructor
public class ExpressTemplatePageQryExecutor {

    private final ExpressTemplateMapper mapper;
    private final ExpressTemplateConvertor convertor;

    public Page<ExpressTemplateCO> execute(ExpressTemplatePageQry qry, PageQuery pageQuery) {
        Page<ExpressTemplateDO> page = mapper.selectPages(qry, pageQuery.build());
        return this.convertor.toMerchantShippingTemplateCO(page);
    }

}
