package org.dromara.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description 行政区划实体类
 * @Author Code Skywalker
 * @Date 2025/12/3 09:32
 */
@Data
@TableName("sys_district")
public class SysDistrict {

    /**
     * 行政区划ID
     */
    @TableId(value = "district_id")
    private Long districtId;

    /**
     * 父级行政区划ID
     */
    private Long parentId;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 行政区划名称
     */
    private String districtName;

    /**
     * 行政区划编码
     */
    private String adcode;

    /**
     * 行政区划级别（country:国家 province:省份 city:市 district:区/县 street:街道）
     */
    private String level;

    /**
     * 行政区划中心点坐标（经度,纬度）
     */
    private String center;

    /**
     * 显示顺序
     */
    private Integer orderNum;

}
