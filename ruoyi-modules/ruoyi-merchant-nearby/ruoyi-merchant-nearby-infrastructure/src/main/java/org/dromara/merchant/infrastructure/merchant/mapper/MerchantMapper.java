package org.dromara.merchant.infrastructure.merchant.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantPageQry;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantDO;

/**
 * @Description 商户Mapper接口
 * @Author Code Skywalker
 * @Date 2025/12/1 15:30
 */
public interface MerchantMapper {

    int deleteByPrimaryKey(Long merchantId);

    int insertSelective(MerchantDO record);

    MerchantDO selectByPrimaryKey(Long merchantId);

    int updateByPrimaryKeySelective(MerchantDO record);

    Page<MerchantDO> selectPages(@Param("qry") MerchantPageQry qry, @Param("page") Page<MerchantDO> page);

}
