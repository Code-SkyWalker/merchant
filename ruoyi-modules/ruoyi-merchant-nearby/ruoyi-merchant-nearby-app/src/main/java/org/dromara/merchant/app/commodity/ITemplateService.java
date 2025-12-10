package org.dromara.merchant.app.commodity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.commodity.dto.data.clientobject.TemplatePageCO;
import org.dromara.merchant.client.commodity.dto.data.command.TemplateCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.TemplateModifyCmd;
import org.dromara.merchant.client.commodity.dto.data.command.query.TemplateQry;
import org.dromara.merchant.domain.commodity.model.Template;

/**
 * @Description 商品模板服务接口
 * @Author Code Skywalker
 * @Date 2025/12/8 14:55
 */
public interface ITemplateService {

    /**
     * 创建商品模板
     *
     * @param cmd 创建商品模板命令
     * @return 是否创建成功
     */
    boolean create(TemplateCreateCmd cmd);

    /**
     * 修改商品模板
     *
     * @param cmd 修改商品模板命令
     * @return 是否修改成功
     */
    boolean modify(TemplateModifyCmd cmd);

    /**
     * 删除商品模板
     *
     * @param id 商品模板ID
     * @return 是否删除成功
     */
    boolean delete(Integer id);

    /**
     * 查询商品模板
     *
     * @param id 商品模板ID
     * @return 商品模板
     */
    Template queryById(Integer id);

    /**
     * 查询商品模板列表
     *
     * @param qry 查询参数
     * @return 商品模板列表
     */
    Page<TemplatePageCO> queryPage(TemplateQry qry, PageQuery page);

}
