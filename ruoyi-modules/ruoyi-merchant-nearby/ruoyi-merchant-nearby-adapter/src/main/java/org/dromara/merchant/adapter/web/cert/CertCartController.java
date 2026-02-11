package org.dromara.merchant.adapter.web.cert;

import cn.dev33.satoken.annotation.SaCheckLogin;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.common.web.core.BaseController;
import org.dromara.merchant.app.cert.service.CertCartService;
import org.dromara.merchant.client.cert.co.CertCartSummaryCO;
import org.dromara.merchant.client.marketing.dto.data.command.PriceCalculationCmd;
import org.dromara.merchant.domain.cert.entity.CertCartItem;
import org.dromara.merchant.domain.marketing.discount.CalculationResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 购物车控制器
 *
 * @author Lion Li
 */
@Validated
@RestController
@RequestMapping("/merchant/cart")
@RequiredArgsConstructor
@SaCheckLogin
public class CertCartController extends BaseController {

    private final CertCartService certCartService;

    /**
     * 添加商品到购物车
     *
     * @param certCartItem 购物车项
     * @return 添加结果
     */
    @PostMapping
    public R<Boolean> addToCertCart(@RequestBody CertCartItem certCartItem) {
        boolean result = certCartService.addToCertCart(certCartItem);
        return result ? R.ok(true) : R.fail(false);
    }

    /**
     * 从购物车移除商品
     *
     * @param productIds 商品ID集合
     * @return 移除结果
     */
    @DeleteMapping
    public R<Boolean> removeFromCertCart(@RequestParam Set<Long> productIds) {
        boolean result = certCartService.removeFromCertCart(LoginHelper.getUserId(), productIds);
        return result ? R.ok(true) : R.fail(false);
    }

    /**
     * 获取购物车分组汇总信息
     *
     * @return 购物车分组汇总CO
     */
    @GetMapping("/summary")
    public R<CertCartSummaryCO> getCertCartSummary() {
        CertCartSummaryCO summary = certCartService.getCertCartSummary(LoginHelper.getUserId());
        return R.ok(summary);
    }

    /**
     * 更新购物车项数量
     *
     * @param add       是否增加，可选值：增加(1)、减少(0)
     * @param productId 商品ID
     * @return 更新结果
     */
    @PutMapping
    public R<Boolean> updateCertCartItemQuantity(@RequestParam Integer add, @RequestParam @NotNull(message = "商品ID不能为空") Long productId) {
        boolean result = certCartService.updateCertCartItemQuantity(add == 1, LoginHelper.getUserId(), productId);
        return result ? R.ok(true) : R.fail(false);
    }

    /**
     * 清空购物车
     *
     * @return 清空结果
     */
    @DeleteMapping("/clear")
    public R<Boolean> clearCertCart() {
        boolean result = certCartService.clearCertCart(LoginHelper.getUserId());
        return result ? R.ok(true) : R.fail(false);
    }

    /**
     * 获取购物车商品总数
     *
     * @return 商品总数
     */
    @GetMapping("/count")
    public R<Integer> getCertCartItemCount() {
        int count = certCartService.certCartItemCount(LoginHelper.getUserId());
        return R.ok(count);
    }

    /**
     * 获取指定购物车商品总金额
     */
    @GetMapping("/amount")
    public R<CalculationResult> getCertCartTotalAmount(@RequestBody PriceCalculationCmd cmd) {
        CalculationResult result = certCartService.specifiedProductTotalAmount(cmd);
        return R.ok(result);
    }
}
