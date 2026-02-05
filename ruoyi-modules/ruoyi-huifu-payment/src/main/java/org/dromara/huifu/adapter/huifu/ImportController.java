package org.dromara.huifu.adapter.huifu;

import com.huifu.bspay.sdk.opps.core.exception.BasePayException;
import com.huifu.bspay.sdk.opps.core.request.V2MerchantBusiStatusQueryRequest;
import com.huifu.bspay.sdk.opps.core.utils.DateTools;
import com.huifu.bspay.sdk.opps.core.utils.SequenceTools;
import org.dromara.common.core.domain.R;
import org.dromara.huifu.client.api.merchant.MerchantImports;
import org.dromara.huifu.client.api.merchant.PersonalImports;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${huifu.import.url}")
    private String huifu_import_url;

    /**
     * 获取商户入驻链接
     */
    @PostMapping("/merchant/web")
    public R<String> webBasicDataImport() {
        return R.ok(null, huifu_import_url);
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

    // TODO: 个人用户入驻，平台分账使用
}
