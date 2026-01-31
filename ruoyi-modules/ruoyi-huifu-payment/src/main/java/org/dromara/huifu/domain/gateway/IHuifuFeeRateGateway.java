package org.dromara.huifu.domain.gateway;


import org.dromara.huifu.domain.model.HuifuFeeRate;

import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/14 15:37
 */
public interface IHuifuFeeRateGateway {

    boolean save(List<HuifuFeeRate> huifuFeeRates, boolean update);

    boolean save(HuifuFeeRate huifuFeeRate);

    boolean delete(Long merchantId);

    List<HuifuFeeRate> queryByMerchantId(Long merchantId);
}
