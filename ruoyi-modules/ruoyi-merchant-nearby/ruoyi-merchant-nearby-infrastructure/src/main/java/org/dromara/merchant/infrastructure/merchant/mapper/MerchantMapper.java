package org.dromara.merchant.infrastructure.merchant.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantCO;
import org.dromara.merchant.client.merchant.dto.data.command.query.MerchantPageQry;
import org.dromara.merchant.domain.merchant.model.Merchant;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantDO;

import java.util.List;

/**
 * @Description 商户Mapper接口
 * @Author Code Skywalker
 * @Date 2025/12/1 15:30
 */
public interface MerchantMapper extends BaseMapperPlus<MerchantDO, MerchantDO> {

    Page<MerchantDO> selectPages(@Param("qry") MerchantPageQry qry, @Param("page") Page<MerchantDO> page);

    Merchant selectByUserId(Long userId);

    /**
     * 查询所有有效商家（活跃且已认证）
     *
     * @return 有效商家列表
     */
    List<MerchantDO> selectAllValidMerchants();

    /**
     * 根据ids分页查询商户
     *
     * @param build           分页参数
     * @param nearbyMerchants 商户ids
     * @return 商户列表
     */
    Page<MerchantCO> queryByIdsPages(Page<Object> build, @Param("nearbyMerchants") List<Long> nearbyMerchants, @Param("categoryId") Long categoryId);

    /**
     * 前端查询商户
     *
     * @param build        分页参数
     * @param merchantName 商户名称
     * @return 商户列表
     */
    Page<MerchantCO> queryPagesFrontend(Page<Object> build, @Param("merchantName") String merchantName);

    /**
     * 根据分类id分页查询商户
     *
     * @param build      分页参数
     * @param categoryId 分类id
     * @return 商户列表
     */
    Page<MerchantCO> queryByCategoryIdPages(Page<Object> build, @Param("categoryId") Long categoryId);
}
