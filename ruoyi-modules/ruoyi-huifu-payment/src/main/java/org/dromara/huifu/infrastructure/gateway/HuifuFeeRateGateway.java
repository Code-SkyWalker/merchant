package org.dromara.huifu.infrastructure.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.huifu.domain.gateway.IHuifuFeeRateGateway;
import org.dromara.huifu.domain.model.HuifuFeeRate;
import org.dromara.huifu.infrastructure.convertor.HuifuFeeRateConvertor;
import org.dromara.huifu.infrastructure.mapper.HuifuFeeRateMapper;
import org.dromara.huifu.infrastructure.mapper.dataobject.HuifuFeeRateDO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/14 15:38
 */
@Component
@RequiredArgsConstructor
public class HuifuFeeRateGateway implements IHuifuFeeRateGateway {

    private final HuifuFeeRateMapper mapper;
    private final HuifuFeeRateConvertor convertor;

    @Override
    public boolean save(List<HuifuFeeRate> rates, boolean update) {
        if (rates == null || rates.isEmpty()) return false;

        if (update) {
            List<HuifuFeeRate> existRates = this.queryByMerchantId(rates.get(0).getMerchantId());
            Map<Integer, BigDecimal> typeMap =
                rates.stream().collect(Collectors.toMap(HuifuFeeRate::getType, HuifuFeeRate::getFeeRate));

            existRates.forEach(existRate -> existRate.setFeeRate(typeMap.get(existRate.getType())));
        }


        return this.mapper.insertOrUpdateBatch(convertor.toDOList(rates));
    }

    @Override
    public boolean save(HuifuFeeRate huifuFeeRate) {
        return this.mapper.insertOrUpdate(convertor.toDO(huifuFeeRate));
    }

    @Override
    public boolean delete(Long merchantId) {
        return this.mapper.deleteByMerchantId(merchantId) > 0;
    }

    @Override
    public List<HuifuFeeRate> queryByMerchantId(Long merchantId) {
        List<HuifuFeeRateDO> huifuFeeRateDOS = this.mapper.queryByMerchantId(merchantId);
        return convertor.toEntityList(huifuFeeRateDOS);
    }
}
