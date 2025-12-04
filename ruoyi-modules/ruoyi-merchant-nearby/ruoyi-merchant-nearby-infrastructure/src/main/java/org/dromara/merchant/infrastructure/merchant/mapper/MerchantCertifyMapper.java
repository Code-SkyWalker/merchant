package org.dromara.merchant.infrastructure.merchant.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantCertifyPageQry;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantCertifyDO;

import java.util.List;

/**
 * @Description 商户审批Mapper接口
 * @Author Code Skywalker
 * @Date 2025/12/1 16:30
 */
@Mapper
public interface MerchantCertifyMapper extends BaseMapperPlus<MerchantCertifyDO, MerchantCertifyDO> {

    /**
     * 根据主键删除商户审批信息
     *
     * @param approvalId 商户审批ID
     * @return 删除数量
     */
    int deleteByPrimaryKey(Long approvalId);


    /**
     * 插入商户审批信息
     *
     * @param record 商户审批DO对象
     * @return 插入数量
     */
    int insertSelective(MerchantCertifyDO record);

    /**
     * 根据主键查询商户审批信息
     *
     * @param approvalId 商户审批ID
     * @return 商户审批DO对象
     */
    MerchantCertifyDO selectByPrimaryKey(Long approvalId);

    /**
     * 根据主键更新商户审批信息
     *
     * @param record 商户审批DO对象
     * @return 更新数量
     */
    int updateByPrimaryKeySelective(MerchantCertifyDO record);


    /**
     * 根据商户ID查询审批信息
     *
     * @param merchantId 商户ID
     * @return 商户审批DO对象
     */
    List<MerchantCertifyDO> selectByMerchantId(Long merchantId);



    Page<MerchantCertifyDO> selectPages(@Param("qry") MerchantCertifyPageQry qry, @Param("page") PageQuery page);
}
