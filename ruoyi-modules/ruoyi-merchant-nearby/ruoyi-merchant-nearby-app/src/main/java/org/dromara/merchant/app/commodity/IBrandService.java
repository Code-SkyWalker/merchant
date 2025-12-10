package org.dromara.merchant.app.commodity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.commodity.dto.data.clientobject.BrandPageCO;
import org.dromara.merchant.client.commodity.dto.data.command.BrandCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.BrandDeleteCmd;
import org.dromara.merchant.client.commodity.dto.data.command.BrandModifyCmd;
import org.dromara.merchant.client.commodity.dto.data.command.query.BrandQry;
import org.dromara.merchant.domain.commodity.model.Brand;

/**
 * @Description 商品品牌服务接口
 * @Author Code Skywalker
 * @Date 2025/12/8 13:16
 */
public interface IBrandService {

    /**
     * 创建
     * @param cmd 创建命令
     * @return 是否创建成功
     */
    boolean create(BrandCreateCmd cmd);

    /**
     * 修改
     * @param cmd 修改命令
     * @return 是否修改成功
     */
    boolean modify(BrandModifyCmd cmd);

    /**
     * 删除
     * @param cmd 删除命令
     * @return 是否删除成功
     */
    boolean delete(BrandDeleteCmd cmd);

    /**
     * 查询
     * @param id 品牌ID
     * @return  品牌
     */
    Brand queryById(Integer id);

    /**
     * 分页查询
     * @param qry 查询参数
     * @param page 分页参数
     * @return 品牌列表
     */
    Page<BrandPageCO> queryPage(BrandQry qry, PageQuery page);
}
