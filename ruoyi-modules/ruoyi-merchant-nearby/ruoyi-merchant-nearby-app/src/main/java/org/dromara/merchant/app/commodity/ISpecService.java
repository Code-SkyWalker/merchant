package org.dromara.merchant.app.commodity;

import org.dromara.merchant.client.commodity.dto.data.command.*;
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
}
