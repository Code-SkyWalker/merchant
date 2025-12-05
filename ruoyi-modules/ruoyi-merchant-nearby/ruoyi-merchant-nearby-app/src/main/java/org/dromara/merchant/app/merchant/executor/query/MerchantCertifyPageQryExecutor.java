package org.dromara.merchant.app.merchant.executor.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantCertifyCO;
import org.dromara.merchant.client.merchant.dto.data.command.query.MerchantCertifyPageQry;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantCertifyConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.MerchantCertifyMapper;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantCertifyDO;
import org.springframework.stereotype.Component;

/**
 * @Description 商户详情查询执行器
 * @Author Code Skywalker
 * @Date 2025/12/1 16:00
 */
@Component
@RequiredArgsConstructor
public class MerchantCertifyPageQryExecutor {

    private final MerchantCertifyMapper mapper;
    private final MerchantCertifyConvertor convertor;

    public Page<MerchantCertifyCO> execute(MerchantCertifyPageQry query, PageQuery page) {
        Page<MerchantCertifyDO> certifyDO = mapper.selectPages(query, page);
        return this.convertor.toMerchantCertifyCO(certifyDO);
    }

}
