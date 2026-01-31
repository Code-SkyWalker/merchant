package org.dromara.huifu.adapter.huifu;

import com.huifu.bspay.sdk.opps.core.exception.BasePayException;
import com.huifu.bspay.sdk.opps.core.request.V2MerchantBusiStatusQueryRequest;
import com.huifu.bspay.sdk.opps.core.utils.DateTools;
import com.huifu.bspay.sdk.opps.core.utils.SequenceTools;
import org.dromara.common.core.domain.R;
import org.dromara.huifu.client.api.merchant.MerchantImports;
import org.dromara.huifu.client.api.merchant.PersonalImports;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 商户进件
 * @Author Code Skywalker
 * @Date 2025-11-10 15:46
 */
@RestController
@RequestMapping("/payment/huifu/import")
public class ImportController {

    private final MerchantImports merchantImports = new MerchantImports();
    private final PersonalImports PersonalImports = new PersonalImports();

    /*https://paas.huifu.com/mer_register/?grantsId=35134c9cbeb5bd57afdefe86&encrypt_msg=UMI8jvPlNx6kPOKAaRjmJ%2BAroc%2BywFQjZR%2B288KcJ1hmCjKtEQ7Xk23RcNdgEovRUmyX6ZMuAiqCzOUJFmA090bhLajTnChXxeIr%2BHHh6ZuANFUFjaDySyIkMKOnl%2FWNyWHT2EBx3zwmRyWpEjk75A%3D%3D&version=1.0.0&presetType=T2&templateId=2739*/
    private static final String default_import_url = "https://paas.huifu.com/mer_register/?grantsId=35134c9cbeb5bd57afdefe86&encrypt_msg=UMI8jvPlNx6kPOKAaRjmJ%2BAroc%2BywFQjZR%2B288KcJ1hmCjKtEQ7Xk23RcNdgEovRUmyX6ZMuAiqCzOUJFmA090bhLajTnChXxeIr%2BHHh6ZuANFUFjaDySyIkMKOnl%2FWNyWHT2EBx3zwmRyWpEjk75A%3D%3D&version=1.0.0&presetType=T2&templateId=2739";

    /**
     * 获取商户入驻链接
     */
    @PostMapping("/merchant/web")
    public R<String> webBasicDataImport() {
        return R.ok(null, default_import_url);
    }

    /**
     * 查询商户入驻进度
     */
    @PostMapping("/merchant/web/query")
    public R<Map<String, Object>> webBasicDataQuery(@RequestParam Integer merchantId) throws BasePayException, IllegalAccessException {
        V2MerchantBusiStatusQueryRequest request = new V2MerchantBusiStatusQueryRequest();
        request.setReqSeqId(SequenceTools.getReqSeqId32());
        request.setReqDate(DateTools.getCurrentDateYYYYMMDD());
        request.setStoreId(merchantId.toString());
        Map<String, Object> result = this.merchantImports.webBasicDataQuery(request);
        return R.ok(null, result);
    }

}
