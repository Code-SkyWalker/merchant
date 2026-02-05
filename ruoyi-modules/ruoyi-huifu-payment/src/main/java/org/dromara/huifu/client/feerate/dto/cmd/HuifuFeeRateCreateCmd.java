package org.dromara.huifu.client.feerate.dto.cmd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/14 14:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HuifuFeeRateCreateCmd {

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 商家比例 百分比值如80% -> 80  三者之和必须等于100
     */
    private BigDecimal merchantRate;

    /**
     * 平台比例 百分比值如15% -> 15  三者之和必须等于100
     */
    private BigDecimal platformRate;

    /**
     * 渠道比例 百分比值如5% -> 5  三者之和必须等于100
     */
    private BigDecimal channelRate;

    /**
     * 总比例
     */
    public BigDecimal totalRate() {
        return merchantRate.add(platformRate).add(channelRate);
    }

    /**
     * 校验比例相加是否等于100
     */
    public boolean valid() {
        return totalRate().compareTo(BigDecimal.valueOf(100)) == 0;
    }

}
