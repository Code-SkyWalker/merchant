package org.dromara.merchant.app.freight;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.freight.dto.data.clientobject.ExpressTemplateCO;
import org.dromara.merchant.client.freight.dto.data.command.ExpressTemplateCreateCmd;
import org.dromara.merchant.client.freight.dto.data.command.ExpressTemplateModifyCmd;
import org.dromara.merchant.client.freight.dto.data.command.query.ExpressTemplatePageQry;
import org.dromara.merchant.domain.freight.model.ExpressArea;

import java.util.List;

/**
 * @Description 商户运费模板服务接口
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
public interface IExpressTemplateService {

    /**
     * 创建商户运费模板
     *
     * @param cmd 创建命令
     * @return 创建的模板ID
     */
    Long create(ExpressTemplateCreateCmd cmd);

    /**
     * 修改商户运费模板
     *
     * @param cmd 修改命令
     * @return 是否修改成功
     */
    boolean modify(ExpressTemplateModifyCmd cmd);

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
     * 根据模板ID查询运费区域
     *
     * @param templateId 模板ID
     * @return 运费区域列表
     */
    List<ExpressArea> queryExpressAreaByTemplateId(Long templateId);

    /**
     * 分页查询商户运费模板
     *
     * @param qry 查询条件
     * @param pageQuery 分页参数
     * @return 运费模板分页结果
     */
    Page<ExpressTemplateCO> queryPage(ExpressTemplatePageQry qry, PageQuery pageQuery);

}
