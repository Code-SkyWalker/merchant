package org.dromara.merchant.app.merchant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantCO;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantCreateCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantModifyCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantPageQry;

/**
 * @Description 商户服务接口
 * @Author Code Skywalker
 * @Date 2025/12/1 16:00
 */
public interface IMerchantService {

    /**
     * 创建商户
     *
     * @param cmd 创建命令
     * @return 是否创建成功
     */
    boolean create(MerchantCreateCmd cmd);

    /**
     * 修改商户
     *
     * @param cmd 修改命令
     * @return 是否修改成功
     */
    boolean modify(MerchantModifyCmd cmd);

    /**
     * 删除商户
     *
     * @param merchantId 商户ID
     * @return 是否删除成功
     */
    boolean delete(Long merchantId);

    /**
     * 根据ID查询商户
     *
     * @param merchantId 商户ID
     * @return 商户客户端对象
     */
    MerchantCO queryById(Long merchantId);

    /**
     * 分页查询商户
     *
     * @param qry 查询条件
     * @param pageQuery 分页参数
     * @return 商户分页结果
     */
    Page<MerchantCO> queryPage(MerchantPageQry qry, PageQuery pageQuery);

}
