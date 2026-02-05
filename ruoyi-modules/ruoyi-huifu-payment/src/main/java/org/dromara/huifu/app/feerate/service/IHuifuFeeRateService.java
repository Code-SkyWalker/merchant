package org.dromara.huifu.app.feerate.service;


import org.dromara.huifu.client.feerate.dto.cmd.HuifuFeeRateCreateCmd;
import org.dromara.huifu.client.feerate.dto.cmd.HuifuFeeRateModifyCmd;
import org.dromara.huifu.domain.model.HuifuFeeRate;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/13 17:34
 */
public interface IHuifuFeeRateService {

    /**
     * 创建汇付配置
     */
    boolean create(HuifuFeeRateCreateCmd cmd);

    /**
     * 修改汇付配置
     */
    boolean modify(HuifuFeeRateModifyCmd cmd);

    /**
     * 删除汇付配置
     */
    boolean delete(Long merchantId);

    /**
     * 根据 租户编号 查询
     */
    List<HuifuFeeRate> queryByMerchantId(Long tenantId);

}
