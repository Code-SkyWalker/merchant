package org.dromara.merchant.app.commodity.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.app.commodity.IParaService;
import org.dromara.merchant.app.commodity.executor.ParaCreateExe;
import org.dromara.merchant.app.commodity.executor.ParaDeleteExe;
import org.dromara.merchant.app.commodity.executor.ParaModifyExe;
import org.dromara.merchant.client.commodity.dto.data.clientobject.ParaPageCO;
import org.dromara.merchant.client.commodity.dto.data.command.ParaCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.ParaModifyCmd;
import org.dromara.merchant.client.commodity.dto.data.command.query.ParaQry;
import org.dromara.merchant.domain.commodity.gateway.IParaGateway;
import org.dromara.merchant.domain.commodity.model.Para;
import org.dromara.merchant.infrastructure.commodity.mapper.ParaMapper;
import org.springframework.stereotype.Component;

/**
 * @Description 商品参数模板服务接口
 * @Author Code Skywalker
 * @Date 2025/12/8 14:11
 */
@Component
@RequiredArgsConstructor
public class ParaService implements IParaService {

    private final ParaCreateExe paraCreateExe;
    private final ParaModifyExe paraModifyExe;
    private final ParaDeleteExe paraDeleteExe;
    private final IParaGateway paraGateway;

    private final ParaMapper paraMapper;


    @Override
    public boolean create(ParaCreateCmd cmd) {
        return this.paraCreateExe.execute(cmd);
    }

    @Override
    public boolean modify(ParaModifyCmd cmd) {
        return this.paraModifyExe.execute(cmd);
    }

    @Override
    public boolean delete(Integer id) {
        return this.paraDeleteExe.execute(id);
    }

    @Override
    public Para queryById(Integer id) {
        return this.paraGateway.queryById(id);
    }

    @Override
    public Page<ParaPageCO> queryPage(ParaQry qry, PageQuery page) {
        return this.paraMapper.selectPage(page.build(), qry);
    }
}
