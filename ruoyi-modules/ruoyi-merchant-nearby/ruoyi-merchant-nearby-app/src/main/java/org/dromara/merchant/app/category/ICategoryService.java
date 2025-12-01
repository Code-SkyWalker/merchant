package org.dromara.merchant.app.category;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.category.dto.data.clientobject.CategoryCO;
import org.dromara.merchant.client.category.dto.data.command.CategoryCreateCmd;
import org.dromara.merchant.client.category.dto.data.command.CategoryModifyCmd;
import org.dromara.merchant.client.category.dto.data.command.CategoryPageQry;


/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025-11-03 14:21
 */
public interface ICategoryService {

    boolean create(CategoryCreateCmd cmd);

    boolean modify(CategoryModifyCmd cmd);

    boolean delete(Long categoryId);

    CategoryCO queryById(Long categoryId);

    Page<CategoryCO> queryPage(CategoryPageQry qry, PageQuery query);

    boolean writeDefaultCategory();
}
