package org.dromara.merchant.app.merchant.executor.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantCO;
import org.dromara.merchant.client.merchant.dto.data.command.query.MerchantPageQry;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.MerchantMapper;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantDO;
import org.springframework.stereotype.Component;

/**
 * @Description 商户分页查询执行器
 * @Author Code Skywalker
 * @Date 2025/12/1 16:00
 */
@Component
@RequiredArgsConstructor
public class MerchantPageQryExecutor {

    private final MerchantMapper mapper;
    private final MerchantConvertor convertor;

    public Page<MerchantCO> execute(MerchantPageQry qry, PageQuery pageQuery) {
        Page<MerchantDO> page = mapper.selectPages(qry, pageQuery.build());
        return this.convertor.toMerchantCO(page);
    }

}
