package org.dromara.merchant.app.commodity.service;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.commodity.ISpecService;
import org.dromara.merchant.app.commodity.executor.SpecCreateExe;
import org.dromara.merchant.app.commodity.executor.SpecDeleteExe;
import org.dromara.merchant.app.commodity.executor.SpecModifyExe;
import org.dromara.merchant.client.commodity.dto.data.command.SpecCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.SpecModifyCmd;
import org.dromara.merchant.domain.commodity.gateway.ISpecGateway;
import org.dromara.merchant.domain.commodity.model.Spec;
import org.springframework.stereotype.Component;

/**
 * @Description 商品规格模板服务接口
 * @Author Code Skywalker
 * @Date 2025/12/8 13:50
 */
@Component
@RequiredArgsConstructor
public class SpecService implements ISpecService {

    private final SpecCreateExe specCreateExe;
    private final SpecModifyExe specModifyExe;
    private final SpecDeleteExe specDeleteExe;
    private final ISpecGateway specGateway;


    @Override
    public boolean create(SpecCreateCmd cmd) {
        return this.specCreateExe.execute(cmd);
    }

    @Override
    public boolean modify(SpecModifyCmd cmd) {
        return this.specModifyExe.execute(cmd);
    }

    @Override
    public boolean delete(Integer id) {
        return this.specDeleteExe.execute(id);
    }

    @Override
    public Spec queryById(Integer id) {
        return this.specGateway.queryById(id);
    }
}