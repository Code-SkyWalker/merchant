package org.dromara.merchant.app.commodity;

import org.dromara.merchant.client.commodity.dto.data.command.ParaCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.ParaModifyCmd;
import org.dromara.merchant.domain.commodity.model.Para;

/**
 * @Description 商品参数模板服务接口
 * @Author Code Skywalker
 * @Date 2025/12/8 14:07
 */
public interface IParaService {

    /**
     * 创建参数模板
     * @param cmd 创建参数
     * @return 创建结果
     */
    boolean create(ParaCreateCmd cmd);

    /**
     * 修改参数模板
     * @param cmd 修改参数
     * @return 修改结果
     */
    boolean modify(ParaModifyCmd cmd);

    /**
     * 删除参数模板
     * @param id 参数ID
     * @return 删除结果
     */
    boolean delete(Integer id);

    /**
     * 根据ID查询参数模板
     * @param id 参数ID
     * @return 参数
     */
    Para queryById(Integer id);
}
