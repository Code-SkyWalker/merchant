package org.dromara.merchant.app.commodity;

import org.dromara.merchant.client.commodity.dto.data.command.CategoryCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.CategoryModifyCmd;
import org.dromara.merchant.domain.commodity.model.Category;

import java.util.List;

/**
 * @Description 商品类目服务
 * @Author Code Skywalker
 * @Date 2025/12/8 15:48
 */
public interface ICategoryService {

    /**
     * 创建商品类目
     * @param cmd 创建命令
     * @return 是否创建成功
     */
    boolean create(CategoryCreateCmd cmd);

    /**
     * 修改商品类目
     * @param cmd 修改命令
     * @return 是否修改成功
     */
    boolean modify(CategoryModifyCmd cmd);

    /**
     * 删除商品类目
     * @param id 类目ID
     * @return 是否删除成功
     */
    boolean delete(Integer id);

    /**
     * 根据ID查询商品类目
     * @param id 类目ID
     * @return 类目
     */
    Category queryById(Integer id);

    /**
     * 根据父类目ID查询子类目
     * @param parentId 父类目ID
     * @return 子类目列表
     */
    List<Category> queryByParentId(Integer parentId);

}
