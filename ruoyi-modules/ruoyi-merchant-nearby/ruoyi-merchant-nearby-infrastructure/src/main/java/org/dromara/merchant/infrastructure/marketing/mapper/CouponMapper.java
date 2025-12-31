package org.dromara.merchant.infrastructure.marketing.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.client.marketing.dto.data.client.CouponCO;
import org.dromara.merchant.client.marketing.dto.data.client.CouponPageCO;
import org.dromara.merchant.client.marketing.dto.data.command.query.CouponPageQry;
import org.dromara.merchant.infrastructure.marketing.mapper.dataobject.CouponDO;

public interface CouponMapper extends BaseMapperPlus<CouponDO, CouponDO> {

    /**
     * 分页查询优惠券
     *
     * @param qry  查询参数
     * @param page 分页参数
     * @return 优惠券列表
     */
    Page<CouponPageCO> queryPages(@Param("qry") CouponPageQry qry, Page<CouponPageCO> page);

    /**
     * 根据优惠券Id查询优惠券
     *
     * @param id 优惠券Id
     * @return 优惠券
     */
    CouponCO selectByCouponId(Long id);

}
