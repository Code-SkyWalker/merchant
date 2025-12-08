package org.dromara.merchant.app.merchant;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerCategoryCO;
import org.dromara.merchant.client.merchant.dto.data.command.MerCategoryCreateCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerCategoryModifyCmd;
import org.dromara.merchant.client.merchant.dto.data.command.query.MerCategoryPageQry;


/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025-11-03 14:21
 */
public interface IMerCategoryService {

    boolean create(MerCategoryCreateCmd cmd);

    boolean modify(MerCategoryModifyCmd cmd);

    boolean delete(Long categoryId);

    MerCategoryCO queryById(Long categoryId);

    Page<MerCategoryCO> queryPage(MerCategoryPageQry qry, PageQuery query);

    boolean writeDefaultCategory();
}
