package org.dromara.system.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.system.domain.SysDistrict;

/**
 * @Description 行政区划业务对象
 * @Author Code Skywalker
 * @Date 2025/12/3 09:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysDistrict.class, reverseConvertGenerate = false)
public class SysDistrictBo extends SysDistrict {

}
