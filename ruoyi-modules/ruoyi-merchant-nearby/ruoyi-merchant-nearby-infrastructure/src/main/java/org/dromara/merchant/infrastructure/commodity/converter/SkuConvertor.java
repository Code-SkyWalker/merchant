package org.dromara.merchant.infrastructure.commodity.converter;

import org.dromara.merchant.client.commodity.dto.data.command.SkuCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.SkuModifyCmd;
import org.dromara.merchant.domain.commodity.model.Sku;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.SkuDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/9 16:41
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SkuConvertor {

    Sku toEntity(SkuDO skuDO);

    SkuDO toDo(Sku sku);

    List<SkuDO> toDoList(List<Sku> sku);

    List<Sku> toEntityList(List<SkuDO> skuDO);

    List<Sku> createCmdToEntityList(List<SkuCreateCmd> cmd);

    List<Sku> modifyCmdToEntityList(List<SkuModifyCmd> cmd);
}
