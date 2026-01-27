package org.dromara.merchant.infrastructure.marketing.converter;

import org.dromara.merchant.client.marketing.dto.data.command.CouponCreateCmd;
import org.dromara.merchant.client.marketing.dto.data.command.CouponModifyCmd;
import org.dromara.merchant.domain.marketing.model.coupon.Coupon;
import org.dromara.merchant.infrastructure.marketing.mapper.dataobject.CouponDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @Description Coupon转换器
 * @Author Code Skywalker
 * @Date 2025/12/26 11:14
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CouponConvertor {

    /**
     * CouponDO转Coupon
     *
     * @param couponDO 优惠券数据对象
     * @return 优惠券实体
     */
    Coupon toEntity(CouponDO couponDO);

    /**
     * Coupon转CouponDO
     *
     * @param coupon 优惠券实体
     * @return 优惠券数据对象
     */
    CouponDO toDo(Coupon coupon);

    /**
     * CouponCreateCmd转Coupon
     *
     * @param cmd 优惠券创建命令
     * @return 优惠券实体
     */
    Coupon toEntity(CouponCreateCmd cmd);

    /**
     * CouponModifyCmd转Coupon
     *
     * @param cmd 优惠券修改命令
     * @return 优惠券实体
     */
    Coupon toEntity(CouponModifyCmd cmd);

    /**
     * CouponDO列表转Coupon列表
     *
     * @param couponDOList 优惠券数据对象列表
     * @return 优惠券实体列表
     */
    List<Coupon> toEntityList(List<CouponDO> couponDOList);

}
