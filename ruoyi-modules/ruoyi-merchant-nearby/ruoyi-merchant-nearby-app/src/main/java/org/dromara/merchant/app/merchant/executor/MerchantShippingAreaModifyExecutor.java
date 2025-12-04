package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantShippingAreaModifyCmd;
import org.dromara.merchant.domain.merchant.gateway.IMerchantShippingAreaGateway;
import org.dromara.merchant.domain.merchant.model.delivery.MerchantShippingArea;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantShippingAreaConvertor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 修改商户配送区域执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
@Component
@RequiredArgsConstructor
public class MerchantShippingAreaModifyExecutor {

    private final IMerchantShippingAreaGateway areaGateway;
    private final MerchantShippingAreaConvertor convertor;

    public boolean execute(MerchantShippingAreaModifyCmd cmd) {
        return areaGateway.save(this.convertor.toMerchantShippingAreaEntity(cmd));
    }

    public boolean execute(List<MerchantShippingAreaModifyCmd> cmds) {

        // 筛选并修改商户配送区域
        List<MerchantShippingAreaModifyCmd> modifyCmds = cmds.stream()
            .filter(area -> !area.getDeleted())
            .toList();
        List<MerchantShippingArea> modifyAreas = this.convertor.modifyCmdsToMerchantShippingAreaEntityList(modifyCmds);
        boolean modifyArea = areaGateway.batchSave(modifyAreas);

        // 筛选并删除商户配送区域
        List<Long> deleteAreaIds = modifyCmds.stream()
            .filter(MerchantShippingAreaModifyCmd::getDeleted)
            .map(MerchantShippingAreaModifyCmd::getAreaId).toList();
        boolean deleteArea = this.areaGateway.deleteByIds(deleteAreaIds);

        return deleteArea && modifyArea;
    }

}
