package org.dromara.system.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.system.domain.bo.SysDistrictBo;
import org.dromara.system.domain.vo.SysDistrictVo;

import java.util.Collection;
import java.util.List;

/**
 * @Description 行政区划Service接口
 * @Author Code Skywalker
 * @Date 2025/12/3 09:34
 */
public interface ISysDistrictService {

    /**
     * 查询行政区划
     */
    SysDistrictVo queryById(Long districtId);

    /**
     * 查询行政区划列表
     */
    TableDataInfo<SysDistrictVo> queryPageList(SysDistrictBo bo, PageQuery pageQuery);

    /**
     * 查询行政区划列表
     */
    List<SysDistrictVo> queryList(SysDistrictBo bo);

    /**
     * 根据父级ID查询子行政区划
     */
    List<SysDistrictVo> selectDistrictListByParentId(Long parentId);


    /**
     * 校验行政区划编码是否唯一
     */
    boolean checkAdcodeUnique(SysDistrictBo bo);

    /**
     * 批量删除行政区划
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 获取高德地图API数据并同步到数据库
     */
    void syncDistrictDataFromGaode(String keywords, Integer subdistrict);
}
