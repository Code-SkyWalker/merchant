package org.dromara.merchant.app.category;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.category.dto.data.clientobject.CategoryDefaultCO;
import org.dromara.merchant.client.category.dto.data.command.DefaultCategoryCreateCmd;
import org.dromara.merchant.client.category.dto.data.command.DefaultCategoryModifyCmd;
import org.dromara.merchant.client.category.dto.data.command.DefaultCategoryPageQry;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025-11-03 14:21
 */
public interface IDefaultCategoryService {

    boolean create(DefaultCategoryCreateCmd cmd);

    boolean modify(DefaultCategoryModifyCmd cmd);

    boolean delete(Long categoryId);

    CategoryDefaultCO queryById(Long categoryId);

    Page<CategoryDefaultCO> queryPage(DefaultCategoryPageQry qry, PageQuery page);

}
