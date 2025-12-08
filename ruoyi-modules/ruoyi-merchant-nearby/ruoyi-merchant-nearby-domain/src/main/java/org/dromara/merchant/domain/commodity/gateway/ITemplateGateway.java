package org.dromara.merchant.domain.commodity.gateway;

import org.dromara.merchant.domain.commodity.model.Template;

/**
 * @Description 商品模板网关接口
 * @Author Code Skywalker
 * @Date 2025/12/8 13:43
 */
public interface ITemplateGateway {

    /**
     * 保存商品模板
     * @param template 商品模板
     * @return 是否保存成功
     */
    boolean save(Template template);

    /**
     * 删除商品模板
     * @param id 商品模板id
     * @return 是否删除成功
     */
    boolean delete(Integer id);

    /**
     * 查询商品模板
     * @param id 商品模板id
     * @return 商品模板
     */
    Template queryById(Integer id);
}
