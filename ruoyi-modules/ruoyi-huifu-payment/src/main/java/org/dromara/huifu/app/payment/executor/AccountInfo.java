package org.dromara.huifu.app.payment.executor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 分账明细
 * @Author Code Skywalker
 * @Date 2025/11/13 13:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfo {

    private String div_amt;
    private String huifu_id;
    private String acct_id;
    private String percentage_div;

}
