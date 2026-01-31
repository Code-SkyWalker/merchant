package org.dromara.merchant.app.commodity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.commodity.dto.data.clientobject.SpecPageCO;
import org.dromara.merchant.client.commodity.dto.data.command.SpecCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.SpecModifyCmd;
import org.dromara.merchant.client.commodity.dto.data.command.query.SpecQry;
import org.dromara.merchant.domain.commodity.model.Spec;

/**
 * @Description 商品规格模板服务接口
 * @Author Code Skywalker
 * @Date 2025/12/8 13:16
 */
public interface ISpecService {

    /**
     * 创建
     * @param cmd 创建命令
     * @return 是否创建成功
     */
    boolean create(SpecCreateCmd cmd);

    /**
     * 修改
     * @param cmd 修改命令
     * @return 是否修改成功
     */
    boolean modify(SpecModifyCmd cmd);

    /**
     * 删除
     * @param id 规格模板ID
     * @return 是否删除成功
     */
    boolean delete(Integer id);

    /**
     * 查询
     * @param id 品牌ID
     * @return  品牌
     */
    Spec queryById(Integer id);

    /**
     * 查询
     * @param qry 查询参数
     * @param page 分页参数
     * @return  规格模板分页数据
     */
    Page<SpecPageCO> queryPage(SpecQry qry, PageQuery page);

}
