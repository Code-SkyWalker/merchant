package org.dromara.merchant.adapter.web.merchant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.merchant.app.merchant.IMerchantCertifyService;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantCertifyCO;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantCertifyApprovalCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantCertifyCreateCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantCertifyModifyCmd;
import org.dromara.merchant.client.merchant.dto.data.command.query.MerchantCertifyPageQry;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商家审批接口
 * @Author Code Skywalker
 * @Date 2025/12/1 16:30
 */
@Validated
@RestController
@RequestMapping("/merchant/certify")
@RequiredArgsConstructor
public class MerchantCertifyController extends BaseController {

    private final IMerchantCertifyService merchantCertifyService;

    /**
     * 创建商家审批信息
     *
     * @param cmd 创建参数
     * @return 创建结果
     */
    @PostMapping
    public R<Boolean> create(@RequestBody MerchantCertifyCreateCmd cmd) {
        boolean created = this.merchantCertifyService.create(cmd);
        return created ? R.ok(true) : R.fail(false);
    }

    /**
     * 删除商家审批信息
     *
     * @param approvalId 商家审批主键
     * @return 删除结果
     */
    @DeleteMapping("/{approvalId}")
    public R<Boolean> delete(@PathVariable Long approvalId) {
        boolean deleted = this.merchantCertifyService.deleteByPrimaryKey(approvalId);
        return deleted ? R.ok(true) : R.fail(false);
    }

    /**
     * 查询商家审批信息
     *
     * @param approvalId 审批主键
     * @return 商家审批信息
     */
    @GetMapping("/{approvalId}")
    public R<MerchantCertifyCO> findByApprovalId(@PathVariable Long approvalId) {
        MerchantCertifyCO merchantCertifyCO = this.merchantCertifyService.findById(approvalId);
        return R.ok(merchantCertifyCO);
    }

    /**
     * 根据商户ID查询最新的商家审批信息
     *
     * @param merchantId 商户ID
     * @return 商家审批信息
     */
    @GetMapping("/latest/{merchantId}")
    public R<MerchantCertifyCO> findLatestByMerchantId(@PathVariable Long merchantId) {
        MerchantCertifyCO merchantCertifyCO = this.merchantCertifyService.findByMerchantId(merchantId);
        return R.ok(merchantCertifyCO);
    }

    /**
     * 分页条件查询商家审批信息
     * @param query 查询条件
     * @param page 分页参数
     * @return 商家审批信息
     */
    @GetMapping("/pages")
    public TableDataInfo<MerchantCertifyCO> findByApprovalStatus(@ModelAttribute MerchantCertifyPageQry query, @ModelAttribute PageQuery page) {
        Page<MerchantCertifyCO> merchantCertifyCOs = this.merchantCertifyService.queryPage(query, page);
        return TableDataInfo.build(merchantCertifyCOs);
    }

    /**
     * 审批商家信息
     * @param cmd 审批参数
     * @return 审批结果
     */
    @PutMapping("/approve")
    public R<Boolean> approve(@RequestBody MerchantCertifyApprovalCmd cmd) {
        boolean approved = this.merchantCertifyService.approveMerchant(cmd.getApprovalId(), cmd.getApprovalStatus(), cmd.getApprovalComment());
        return approved ? R.ok(true) : R.fail(false);
    }

}
