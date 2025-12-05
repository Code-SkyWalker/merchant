package org.dromara.merchant.app.merchant.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.app.merchant.IMerchantCertifyService;
import org.dromara.merchant.app.merchant.executor.MerchantCertifyApprovalExecutor;
import org.dromara.merchant.app.merchant.executor.MerchantCertifyCreateExecutor;
import org.dromara.merchant.app.merchant.executor.MerchantCertifyDeleteExecutor;
import org.dromara.merchant.app.merchant.executor.MerchantCertifyModifyExecutor;
import org.dromara.merchant.app.merchant.executor.query.MerchantCertifyDetailQryExecutor;
import org.dromara.merchant.app.merchant.executor.query.MerchantCertifyPageQryExecutor;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantCertifyCO;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantCertifyCreateCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantCertifyModifyCmd;
import org.dromara.merchant.client.merchant.dto.data.command.query.MerchantCertifyPageQry;
import org.springframework.stereotype.Service;

/**
 * @Description 商户审批应用服务实现
 * @Author Code Skywalker
 * @Date 2025/12/1 16:30
 */
@Service
@RequiredArgsConstructor
public class MerchantCertifyService implements IMerchantCertifyService {

    private final MerchantCertifyCreateExecutor createExecutor;
    private final MerchantCertifyDeleteExecutor deleteExecutor;
    private final MerchantCertifyModifyExecutor modifyExecutor;
    private final MerchantCertifyDetailQryExecutor detailQryExecutor;
    private final MerchantCertifyApprovalExecutor approvalExecutor;
    private final MerchantCertifyPageQryExecutor pageQryExecutor;

    /**
     * 创建商户审批
     *
     * @param cmd 创建命令
     * @return 商户审批客户端对象
     */
    @Override
    public boolean create(MerchantCertifyCreateCmd cmd) {
        return this.createExecutor.execute(cmd);
    }

    /**
     * 修改商户审批
     *
     * @param cmd 修改命令
     * @return 商户审批客户端对象
     */
    @Override
    public boolean modify(MerchantCertifyModifyCmd cmd) {
        return this.modifyExecutor.execute(cmd);
    }

    /**
     * 根据商户ID查询商户审批
     *
     * @param approvalId 审批id
     * @return 商户审批客户端对象
     */
    @Override
    public MerchantCertifyCO findById(Long approvalId) {
        return this.detailQryExecutor.execute(approvalId);
    }

    /**
     * 分页查询商户审批
     *
     * @param qry   查询参数
     * @param query 分页参数
     * @return 商户审批列表
     */
    @Override
    public Page<MerchantCertifyCO> queryPage(MerchantCertifyPageQry qry, PageQuery query) {
        return this.pageQryExecutor.execute(qry, query);
    }

    /**
     * 删除商户审批
     *
     * @param approvalId 审批ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteByPrimaryKey(Long approvalId) {
        MerchantCertifyCO certify = this.findById(approvalId);
        if (certify == null) return false;
        if (certify.getApprovalStatus().equals("APPROVED")) throw new ServiceException("该商户已通过审批，无法删除！");
        return this.deleteExecutor.execute(approvalId);
    }

    /**
     * 审批商户信息
     *
     * @param approvalId      审批ID
     * @param approvalStatus  审批状态
     * @param approvalComment 审批意见
     * @return 是否审批成功
     */
    @Override
    public boolean approveMerchant(Long approvalId, String approvalStatus, String approvalComment) {
        return this.approvalExecutor.execute(approvalId, approvalStatus, approvalComment);
    }
}
