package org.dromara.merchant.app.freight.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.freight.dto.data.command.ExpressAreaModifyCmd;
import org.dromara.merchant.domain.freight.gateway.IExpressAreaGateway;
import org.dromara.merchant.domain.freight.model.ExpressArea;
import org.dromara.merchant.infrastructure.freight.converter.ExpressAreaConvertor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 修改商户配送区域执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
@Component
@RequiredArgsConstructor
public class ExpressAreaModifyExecutor {

    private final IExpressAreaGateway areaGateway;
    private final ExpressAreaConvertor convertor;

    public boolean execute(ExpressAreaModifyCmd cmd) {
        return areaGateway.save(this.convertor.toMerchantShippingAreaEntity(cmd));
    }

    public boolean execute(List<ExpressAreaModifyCmd> cmds) {

        // 筛选并修改商户配送区域
        List<ExpressAreaModifyCmd> modifyCmds = cmds.stream()
            .filter(area -> !area.getDeleted())
            .toList();
        List<ExpressArea> modifyAreas = this.convertor.modifyCmdsToMerchantShippingAreaEntityList(modifyCmds);
        boolean modifyArea = areaGateway.batchSave(modifyAreas);

        // 筛选并删除商户配送区域
        List<Long> deleteAreaIds = modifyCmds.stream()
            .filter(ExpressAreaModifyCmd::getDeleted)
            .map(ExpressAreaModifyCmd::getAreaId).toList();
        boolean deleteArea = this.areaGateway.deleteByIds(deleteAreaIds);

        return deleteArea && modifyArea;
    }

}
