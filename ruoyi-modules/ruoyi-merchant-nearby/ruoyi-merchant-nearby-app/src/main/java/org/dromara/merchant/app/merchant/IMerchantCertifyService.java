package org.dromara.merchant.app.merchant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantCertifyCO;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantCertifyCreateCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantCertifyModifyCmd;
import org.dromara.merchant.client.merchant.dto.data.command.query.MerchantCertifyPageQry;

/**
 * @Description 商户审批应用服务接口
 * @Author Code Skywalker
 * @Date 2025/12/1 16:30
 */
public interface IMerchantCertifyService {

    /**
     * 创建商户审批
     *
     * @param cmd 创建命令
     * @return 商户审批客户端对象
     */
    boolean create(MerchantCertifyCreateCmd cmd);

    /**
     * 修改商户审批
     *
     * @param cmd 修改命令
     * @return 商户审批客户端对象
     */
    boolean modify(MerchantCertifyModifyCmd cmd);

    /**
     * 根据商户ID查询商户审批
     *
     * @param approvalId 审批id
     * @return 商户审批客户端对象
     */
    MerchantCertifyCO findById(Long approvalId);

    /**
     * 分页查询商户审批
     *
     * @param qry 查询参数
     * @param query 分页参数
     * @return 商户审批客户端对象列表
     */
    Page<MerchantCertifyCO> queryPage(MerchantCertifyPageQry qry, PageQuery query);


    /**
     * 删除商户审批
     *
     * @param approvalId 审批ID
     * @return 是否删除成功
     */
    boolean deleteByPrimaryKey(Long approvalId);

    /**
     * 审批商户信息
     *
     * @param approvalId 审批ID
     * @param approvalStatus 审批状态
     * @param approvalComment 审批意见
     * @return 是否审批成功
     */
    boolean approveMerchant(Long approvalId, String approvalStatus, String approvalComment);
}
