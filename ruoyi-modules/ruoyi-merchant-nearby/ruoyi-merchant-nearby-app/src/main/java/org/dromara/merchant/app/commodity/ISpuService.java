package org.dromara.merchant.app.commodity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.commodity.dto.data.clientobject.SpuDetailCO;
import org.dromara.merchant.client.commodity.dto.data.clientobject.SpuPageCO;
import org.dromara.merchant.client.commodity.dto.data.command.SpuCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.SpuModifyCmd;
import org.dromara.merchant.client.commodity.dto.data.command.query.SpuQry;
import org.dromara.merchant.domain.commodity.model.Spu;

/**
 * @Description 商品SPU服务接口
 * @Author Code Skywalker
 * @Date 2025/12/9 14:46
 */
public interface ISpuService {

    /**
     * 创建商品
     * @param cmd 创建参数
     * @return 创建结果
     */
    boolean create(SpuCreateCmd cmd);

    /**
     * 修改商品
     * @param cmd 修改参数
     * @return 修改结果
     */
    boolean modify(SpuModifyCmd cmd);

    /**
     * 删除商品
     * @param id 商品ID
     * @return 删除结果
     */
    boolean delete(Long id);

    /**
     * 查询商品
     * @param id 商品ID
     * @return 商品信息
     */
    Spu queryById(Long id);

    /**
     * 分页查询商品
     * @param qry 查询参数
     * @param page 分页参数
     * @return 商品列表
     */
    Page<SpuPageCO> queryPage(SpuQry qry, PageQuery page);

    /**
     * 查询商品详情
     * @param id 商品ID
     * @return 商品详情
     */
    SpuDetailCO queryDetailById(Long id);

}
