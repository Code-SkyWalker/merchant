package org.dromara.merchant.app.merchant.executor.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantShippingTemplateCO;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantShippingTemplatePageQry;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantShippingTemplateConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.MerchantShippingTemplateMapper;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantShippingTemplateDO;
import org.springframework.stereotype.Component;

/**
 * @Description 商户运费模板分页查询执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Component
@RequiredArgsConstructor
public class MerchantShippingTemplatePageQryExecutor {

    private final MerchantShippingTemplateMapper mapper;
    private final MerchantShippingTemplateConvertor convertor;

    public Page<MerchantShippingTemplateCO> execute(MerchantShippingTemplatePageQry qry, PageQuery pageQuery) {
        Page<MerchantShippingTemplateDO> page = mapper.selectPages(qry, pageQuery.build());
        return this.convertor.toMerchantShippingTemplateCO(page);
    }

}