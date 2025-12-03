package org.dromara.system.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.system.domain.SysDistrict;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description 行政区划视图对象
 * @Author Code Skywalker
 * @Date 2025/12/3 09:36
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SysDistrict.class)
public class SysDistrictVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 971049136426751108L;

    /**
     * 行政区划ID
     */
    @ExcelProperty(value = "行政区划ID")
    private Long districtId;

    /**
     * 父级行政区划ID
     */
    @ExcelProperty(value = "父级行政区划ID")
    private Long parentId;

    /**
     * 祖级列表
     */
    @ExcelProperty(value = "祖级列表")
    private String ancestors;

    /**
     * 行政区划名称
     */
    @ExcelProperty(value = "行政区划名称")
    private String districtName;

    /**
     * 行政区划编码
     */
    @ExcelProperty(value = "行政区划编码")
    private String adcode;

    /**
     * 行政区划级别
     */
    @ExcelProperty(value = "行政区划级别")
    private String level;

    /**
     * 行政区划中心点坐标
     */
    @ExcelProperty(value = "中心点坐标")
    private String center;


}
