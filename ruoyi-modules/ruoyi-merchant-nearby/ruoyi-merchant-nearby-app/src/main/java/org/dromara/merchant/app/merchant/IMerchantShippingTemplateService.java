package org.dromara.merchant.app.merchant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantShippingTemplateCO;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantShippingTemplateCreateCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantShippingTemplateModifyCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantShippingTemplatePageQry;

/**
 * @Description 商户运费模板服务接口
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
public interface IMerchantShippingTemplateService {

    /**
     * 创建商户运费模板
     *
     * @param cmd 创建命令
     * @return 创建的模板ID
     */
    Long create(MerchantShippingTemplateCreateCmd cmd);

    /**
     * 修改商户运费模板
     *
     * @param cmd 修改命令
     * @return 是否修改成功
     */
    boolean modify(MerchantShippingTemplateModifyCmd cmd);

    /**
     * 删除商户运费模板
     *
     * @param templateId 模板ID
     * @return 是否删除成功
     */
    boolean delete(Long templateId);

    /**
     * 设置默认运费模板
     *
     * @param templateId 模板ID
     * @param merchantId 商户ID
     * @return 是否设置成功
     */
    boolean setDefault(Long templateId, Long merchantId);

    /**
     * 获取商户默认运费模板
     *
     * @param merchantId 商户ID
     * @return 默认运费模板
     */
    MerchantShippingTemplateCO getDefaultByMerchantId(Long merchantId);

    /**
     * 根据ID查询商户运费模板
     *
     * @param templateId 模板ID
     * @return 运费模板客户端对象
     */
    MerchantShippingTemplateCO queryById(Long templateId);

    /**
     * 分页查询商户运费模板
     *
     * @param qry 查询条件
     * @param pageQuery 分页参数
     * @return 运费模板分页结果
     */
    Page<MerchantShippingTemplateCO> queryPage(MerchantShippingTemplatePageQry qry, PageQuery pageQuery);

}
