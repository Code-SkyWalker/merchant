package org.dromara.merchant.domain.merchant.gateway;

import org.dromara.merchant.domain.merchant.model.MerchantShippingArea;

import java.util.List;

/**
 * @Description 商户配送区域网关接口
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
public interface IMerchantShippingAreaGateway {

    /**
     * 保存商户配送区域
     *
     * @param area 配送区域实体
     * @return 是否保存成功
     */
    boolean save(MerchantShippingArea area);

    /**
     * 批量保存商户配送区域
     *
     * @param areas 配送区域列表
     * @return 是否保存成功
     */
    boolean batchSave(List<MerchantShippingArea> areas);

    /**
     * 根据ID查询商户配送区域
     *
     * @param areaId 配送区域ID
     * @return 配送区域实体
     */
    MerchantShippingArea findById(Long areaId);

    /**
     * 删除商户配送区域
     *
     * @param areaId 配送区域ID
     * @return 是否删除成功
     */
    boolean deleteById(Long areaId);

    /**
     * 删除商户配送区域
     *
     * @param areaIds 配送区域ID
     * @return 是否删除成功
     */
    boolean deleteByIds(List<Long> areaIds);

    /**
     * 根据模板ID删除所有配送区域
     *
     * @param templateId 模板ID
     * @return 是否删除成功
     */
    boolean deleteByTemplateId(Long templateId);

    /**
     * 根据模板ID查询所有配送区域
     *
     * @param templateId 模板ID
     * @return 配送区域列表
     */
    List<MerchantShippingArea> findByTemplateId(Long templateId);
}
