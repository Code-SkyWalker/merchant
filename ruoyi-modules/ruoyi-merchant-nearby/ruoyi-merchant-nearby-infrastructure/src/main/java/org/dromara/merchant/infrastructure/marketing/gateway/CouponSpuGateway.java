package org.dromara.merchant.infrastructure.marketing.gateway;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.marketing.gateway.ICouponSpuGateway;
import org.dromara.merchant.domain.marketing.model.coupon.CouponSpu;
import org.dromara.merchant.infrastructure.marketing.converter.CouponSpuConvertor;
import org.dromara.merchant.infrastructure.marketing.mapper.CouponSpuMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description 优惠券与商品关联数据访问接口实现
 * @Author Code Skywalker
 * @Date 2025/12/29 16:49
 */
@Component
@RequiredArgsConstructor
public class CouponSpuGateway implements ICouponSpuGateway {

    private final CouponSpuMapper mapper;
    private final CouponSpuConvertor convertor;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean save(List<CouponSpu> couponSpus) {
        if (CollUtil.isEmpty(couponSpus)) return false;

        // 如果 deleted 为 true 则是删除, 执行删除操作; 否则执行插入或者修改操作
        Map<Boolean, List<CouponSpu>> grouped = couponSpus.stream()
            .collect(Collectors.groupingBy(CouponSpu::getDeleted));

        boolean result = true;

        // 处理删除操作
        List<CouponSpu> toDelete = grouped.get(true);
        if (CollUtil.isNotEmpty(toDelete)) {
            List<Long> idList = toDelete.stream().map(CouponSpu::getId).toList();
            result = mapper.deleteByIds(idList) > 0;
        }

        // 处理插入或更新操作
        List<CouponSpu> toSave = grouped.get(false);
        if (CollUtil.isNotEmpty(toSave)) {
            boolean insertResult = mapper.insertOrUpdateBatch(this.convertor.toDO(toSave));
            result = result && insertResult;
        }

        return result;
    }


    @Override
    public boolean deleteByCouponId(Long couponId) {
        return this.mapper.deleteByCouponId(couponId) > 0;
    }

    @Override
    public List<Long> queryCouponIdsBySpuId(Long spuId) {
        return this.mapper.selectCouponIdsBySpuId(spuId);
    }
}
