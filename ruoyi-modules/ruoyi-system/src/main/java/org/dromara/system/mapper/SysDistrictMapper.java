package org.dromara.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.system.domain.SysDistrict;
import org.dromara.system.domain.vo.SysDistrictVo;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/3 09:33
 */
@Mapper
public interface SysDistrictMapper extends BaseMapperPlus<SysDistrict, SysDistrictVo> {
}
