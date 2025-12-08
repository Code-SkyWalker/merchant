package org.dromara.merchant.app.merchant;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerCategoryDefaultCO;
import org.dromara.merchant.client.merchant.dto.data.command.MerCategoryDefaultCreateCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerCategoryDefaultModifyCmd;
import org.dromara.merchant.client.merchant.dto.data.command.query.MerCategoryDefaultPageQry;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025-11-03 14:21
 */
public interface IMerCategoryDefaultService {

    boolean create(MerCategoryDefaultCreateCmd cmd);

    boolean modify(MerCategoryDefaultModifyCmd cmd);

    boolean delete(Long categoryId);

    MerCategoryDefaultCO queryById(Long categoryId);

    Page<MerCategoryDefaultCO> queryPage(MerCategoryDefaultPageQry qry, PageQuery page);

}
