package org.dromara.huifu.app.payment.executor;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description 分账对象
 * @Author Code Skywalker
 * @Date 2025/11/13 13:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountSplitBunch {
    private String percentage_flag = "Y";
    private String is_clean_split = "Y";

    private List<AccountInfo> acct_infos;

    public String toString() {
        return JSON.toJSONString(this);
    }
}
