package org.dromara.merchant.infrastructure.marketing.converter;

import org.dromara.merchant.client.marketing.dto.data.command.CouponSpu;
import org.dromara.merchant.infrastructure.marketing.mapper.dataobject.CouponDO;
import org.dromara.merchant.infrastructure.marketing.mapper.dataobject.CouponSpuDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @Description CouponSpu转换器
 * @Author Code Skywalker
 * @Date 2025/12/26 11:14
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CouponSpuConvertor {

    /**
     * CouponSpuDO 转 CouponSpu
     *
     * @param couponDOList CouponSpuDO列表
     * @return CouponSpu列表
     */
    List<org.dromara.merchant.domain.marketing.model.activity.CouponSpu> toEntity(List<CouponDO> couponDOList);

    /**
     * CouponSpu 转 CouponSpuDO
     *
     * @param couponSpuList CouponSpu
     * @return CouponSpuDO列表
     */
    List<CouponSpuDO> toDO(List<org.dromara.merchant.domain.marketing.model.activity.CouponSpu> couponSpuList);

    /**
     * CouponSpuCmd 转 CouponSpu
     *
     * @param couponSpuList CouponSpuCmd列表
     * @return CouponSpu列表
     */
    List<org.dromara.merchant.domain.marketing.model.activity.CouponSpu> cmdToEntity(List<CouponSpu> couponSpuList);

}
