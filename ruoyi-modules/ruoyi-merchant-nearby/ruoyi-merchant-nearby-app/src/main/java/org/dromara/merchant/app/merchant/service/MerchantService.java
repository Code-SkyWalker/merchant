package org.dromara.merchant.app.merchant.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.app.merchant.IMerchantService;
import org.dromara.merchant.app.merchant.executor.MerchantCreateExecutor;
import org.dromara.merchant.app.merchant.executor.MerchantDeleteExecutor;
import org.dromara.merchant.app.merchant.executor.MerchantModifyExecutor;
import org.dromara.merchant.app.merchant.executor.query.MerchantDetailQryExecutor;
import org.dromara.merchant.app.merchant.executor.query.MerchantPageQryExecutor;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantCO;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantCreateCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantModifyCmd;
import org.dromara.merchant.client.merchant.dto.data.command.query.MerchantPageQry;
import org.springframework.stereotype.Service;

/**
 * @Description 商户服务实现
 * @Author Code Skywalker
 * @Date 2025/12/1 16:00
 */
@Service
@RequiredArgsConstructor
public class MerchantService implements IMerchantService {

    private final MerchantCreateExecutor createExecutor;
    private final MerchantDeleteExecutor deleteExecutor;
    private final MerchantModifyExecutor modifyExecutor;
    private final MerchantDetailQryExecutor detailQryExecutor;
    private final MerchantPageQryExecutor pageQryExecutor;

    @Override
    public boolean create(MerchantCreateCmd cmd) {
        return createExecutor.execute(cmd);
    }

    @Override
    public boolean modify(MerchantModifyCmd cmd) {
        return modifyExecutor.execute(cmd);
    }

    @Override
    public boolean delete(Long merchantId) {
        return deleteExecutor.execute(merchantId);
    }

    @Override
    public MerchantCO queryById(Long merchantId) {
        return detailQryExecutor.execute(merchantId);
    }

    @Override
    public Page<MerchantCO> queryPage(MerchantPageQry qry, PageQuery pageQuery) {
        return pageQryExecutor.execute(qry, pageQuery);
    }

}
