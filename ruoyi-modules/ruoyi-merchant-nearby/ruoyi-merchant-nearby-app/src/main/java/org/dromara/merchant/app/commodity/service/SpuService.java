package org.dromara.merchant.app.commodity.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.app.commodity.ISkuService;
import org.dromara.merchant.app.commodity.ISpuService;
import org.dromara.merchant.app.commodity.executor.SpuCreateExe;
import org.dromara.merchant.app.commodity.executor.SpuDeleteExe;
import org.dromara.merchant.app.commodity.executor.SpuModifyExe;
import org.dromara.merchant.client.commodity.dto.data.clientobject.SpuDetailCO;
import org.dromara.merchant.client.commodity.dto.data.clientobject.SpuPageCO;
import org.dromara.merchant.client.commodity.dto.data.command.SkuCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.SpuCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.SpuModifyCmd;
import org.dromara.merchant.client.commodity.dto.data.command.query.SpuQry;
import org.dromara.merchant.domain.commodity.gateway.ISpuGateway;
import org.dromara.merchant.domain.commodity.model.Spu;
import org.dromara.merchant.infrastructure.commodity.mapper.SpuMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/9 14:49
 */
@Component
@RequiredArgsConstructor
public class SpuService implements ISpuService {

    private final SpuCreateExe spuCreateExe;
    private final SpuModifyExe spuModifyExe;
    private final SpuDeleteExe spuDeleteExe;

    private final ISpuGateway spuGateway;

    private final ISkuService skuService;

    private final SpuMapper spuMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean create(SpuCreateCmd cmd) {
        Long spuId = this.spuCreateExe.execute(cmd);
        if (spuId == null) return false;

        List<SkuCreateCmd> skuCreateCmdList = cmd.getSkus().stream().peek(sku -> sku.setSpuId(spuId)).toList();
        return skuService.create(skuCreateCmdList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean modify(SpuModifyCmd cmd) {
        return this.spuModifyExe.execute(cmd) && skuService.modify(cmd.getSkus());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Long id) {
        return this.spuDeleteExe.execute(id) && skuService.deleteBySpuId(id);
    }

    @Override
    public Spu queryById(Long id) {
        return this.spuGateway.findById(id);
    }

    @Override
    public Page<SpuPageCO> queryPage(SpuQry qry, PageQuery page) {
        return this.spuMapper.selectPage(page.build(), qry);
    }

    @Override
    public SpuDetailCO queryDetailById(Long id) {
        return this.spuMapper.selectDetailById(id);
    }
}
