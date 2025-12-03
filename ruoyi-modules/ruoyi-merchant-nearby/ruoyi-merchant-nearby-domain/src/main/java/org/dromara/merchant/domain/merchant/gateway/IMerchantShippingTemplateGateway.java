package org.dromara.merchant.domain.merchant.gateway;

import org.dromara.merchant.domain.merchant.model.MerchantShippingTemplate;

/**
 * @Description 商户运费模板网关接口
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
public interface IMerchantShippingTemplateGateway {

    /**
     * 保存商户运费模板
     *
     * @param template 运费模板实体
     * @return 是否保存成功
     */
    boolean save(MerchantShippingTemplate template);

    /**
     * 根据ID查询商户运费模板
     *
     * @param templateId 模板ID
     * @return 运费模板实体
     */
    MerchantShippingTemplate findById(Long templateId);

    /**
     * 删除商户运费模板
     *
     * @param templateId 模板ID
     * @return 是否删除成功
     */
    boolean deleteById(Long templateId);

    /**
     * 设置默认运费模板
     *
     * @param templateId 模板ID
     * @param merchantId 商户ID
     * @return 是否设置成功
     */
    boolean setDefault(Long templateId, Long merchantId);

    /**
     * 取消商户所有模板的默认状态
     *
     * @param merchantId 商户ID
     * @return 是否取消成功
     */
    boolean cancelDefaultByMerchantId(Long merchantId);

    /**
     * 获取商户默认运费模板
     *
     * @param merchantId 商户ID
     * @return 默认运费模板实体
     */
    MerchantShippingTemplate getDefaultByMerchantId(Long merchantId);

}