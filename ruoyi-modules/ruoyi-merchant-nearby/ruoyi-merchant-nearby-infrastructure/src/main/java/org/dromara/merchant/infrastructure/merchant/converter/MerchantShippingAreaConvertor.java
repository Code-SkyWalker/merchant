package org.dromara.merchant.infrastructure.merchant.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantShippingAreaCO;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantShippingAreaCreateCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantShippingAreaModifyCmd;
import org.dromara.merchant.domain.merchant.model.delivery.MerchantShippingArea;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantShippingAreaDO;
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
public interface MerchantShippingAreaConvertor {

    /**
     * 转换配送区域实体到DO对象
     *
     * @param entity 配送区域实体
     * @return 配送区域DO对象
     */
    MerchantShippingAreaDO toMerchantShippingAreaDO(MerchantShippingArea entity);

    /**
     * 转换配送区域实体到DO对象
     *
     * @param entities 配送区域实体
     * @return 配送区域DO对象
     */
    List<MerchantShippingAreaDO> toMerchantShippingAreaDO(List<MerchantShippingArea> entities);

    /**
     * 转换DO对象到配送区域实体
     *
     * @param dataObject 配送区域DO对象
     * @return 配送区域实体
     */
    MerchantShippingArea toMerchantShippingAreaEntity(MerchantShippingAreaDO dataObject);

    /**
     * 转换创建命令到配送区域实体
     *
     * @param cmd 创建命令
     * @return 配送区域实体
     */
    MerchantShippingArea toMerchantShippingAreaEntity(MerchantShippingAreaCreateCmd cmd);

    /**
     * 转换创建命令到配送区域实体
     *
     * @param cmds 创建命令
     * @return 配送区域实体
     */
    List<MerchantShippingArea> createCmdsToMerchantShippingAreaEntityList(List<MerchantShippingAreaCreateCmd> cmds);

    /**
     * 转换修改命令到配送区域实体
     *
     * @param cmd 修改命令
     * @return 配送区域实体
     */
    MerchantShippingArea toMerchantShippingAreaEntity(MerchantShippingAreaModifyCmd cmd);

    /**
     * 转换修改命令到配送区域实体
     *
     * @param cmds 修改命令
     * @return 配送区域实体
     */
    List<MerchantShippingArea> modifyCmdsToMerchantShippingAreaEntityList(List<MerchantShippingAreaModifyCmd> cmds);

    /**
     * 转换DO对象到客户端对象
     *
     * @param dataObject 配送区域DO对象
     * @return 客户端对象
     */
    MerchantShippingAreaCO toMerchantShippingAreaCO(MerchantShippingAreaDO dataObject);

    /**
     * 批量转换DO对象到客户端对象
     *
     * @param dataObject 配送区域DO对象
     * @return 批量客户端对象
     */
    List<MerchantShippingAreaCO> toMerchantShippingAreaListCO(List<MerchantShippingAreaDO> dataObject);

    /**
     * 批量转换DO对象到客户端对象
     *
     * @param dataObject 配送区域DO对象
     * @return 批量客户端对象
     */
    Page<MerchantShippingAreaCO> toMerchantShippingAreaPageCO(Page<MerchantShippingAreaDO> dataObject);

}
