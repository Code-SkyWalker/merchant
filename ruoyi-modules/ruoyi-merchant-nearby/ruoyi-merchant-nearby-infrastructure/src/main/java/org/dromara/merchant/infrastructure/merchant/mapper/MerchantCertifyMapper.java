package org.dromara.merchant.infrastructure.merchant.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantCertifyCO;
import org.dromara.merchant.client.merchant.dto.data.command.query.MerchantCertifyPageQry;
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


    /**
     * 根据商户ID查询最新的审批信息
     *
     * @param merchantId 商户ID
     * @return 商户审批DO对象
     */
    MerchantCertifyCO selectLatestByMerchantId(Long merchantId);

    /**
     * 分页查询商户审批信息
     *
     * @param page 分页参数
     * @param qry  查询参数
     * @return 商户审批DO对象列表
     */
    Page<MerchantCertifyDO> selectPages(Page<MerchantCertifyDO> page, @Param("qry") MerchantCertifyPageQry qry);

    /**
     * 取消商户审批
     *
     * @param approvalId 审批ID
     * @return 删除数量
     */
    int cancelApproval(@Param("approvalId") Long approvalId);
}
