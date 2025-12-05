package org.dromara.merchant.infrastructure.freight.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.merchant.client.freight.dto.data.clientobject.ExpressAreaCO;
import org.dromara.merchant.client.freight.dto.data.command.ExpressAreaCreateCmd;
import org.dromara.merchant.client.freight.dto.data.command.ExpressAreaModifyCmd;
import org.dromara.merchant.domain.freight.model.ExpressArea;
import org.dromara.merchant.infrastructure.freight.gateway.dataobject.ExpressAreaDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @Description 商户配送区域转换器
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpressAreaConvertor {

    /**
     * 转换配送区域实体到DO对象
     *
     * @param entity 配送区域实体
     * @return 配送区域DO对象
     */
    ExpressAreaDO toMerchantShippingAreaDO(ExpressArea entity);

    /**
     * 转换配送区域实体到DO对象
     *
     * @param entities 配送区域实体
     * @return 配送区域DO对象
     */
    List<ExpressAreaDO> toMerchantShippingAreaDO(List<ExpressArea> entities);

    /**
     * 转换DO对象到配送区域实体
     *
     * @param dataObject 配送区域DO对象
     * @return 配送区域实体
     */
    ExpressArea toMerchantShippingAreaEntity(ExpressAreaDO dataObject);

    /**
     * 转换创建命令到配送区域实体
     *
     * @param cmd 创建命令
     * @return 配送区域实体
     */
    ExpressArea toMerchantShippingAreaEntity(ExpressAreaCreateCmd cmd);

    /**
     * 转换创建命令到配送区域实体
     *
     * @param cmds 创建命令
     * @return 配送区域实体
     */
    List<ExpressArea> createCmdsToMerchantShippingAreaEntityList(List<ExpressAreaCreateCmd> cmds);

    /**
     * 转换修改命令到配送区域实体
     *
     * @param cmd 修改命令
     * @return 配送区域实体
     */
    ExpressArea toMerchantShippingAreaEntity(ExpressAreaModifyCmd cmd);

    /**
     * 转换修改命令到配送区域实体
     *
     * @param cmds 修改命令
     * @return 配送区域实体
     */
    List<ExpressArea> modifyCmdsToMerchantShippingAreaEntityList(List<ExpressAreaModifyCmd> cmds);

    /**
     * 转换DO对象到客户端对象
     *
     * @param dataObject 配送区域DO对象
     * @return 客户端对象
     */
    ExpressAreaCO toMerchantShippingAreaCO(ExpressAreaDO dataObject);

    /**
     * 批量转换DO对象到客户端对象
     *
     * @param dataObject 配送区域DO对象
     * @return 批量客户端对象
     */
    List<ExpressAreaCO> toMerchantShippingAreaListCO(List<ExpressAreaDO> dataObject);

    /**
     * 批量转换DO对象到客户端对象
     *
     * @param dataObject 配送区域DO对象
     * @return 批量客户端对象
     */
    Page<ExpressAreaCO> toMerchantShippingAreaPageCO(Page<ExpressAreaDO> dataObject);

}
