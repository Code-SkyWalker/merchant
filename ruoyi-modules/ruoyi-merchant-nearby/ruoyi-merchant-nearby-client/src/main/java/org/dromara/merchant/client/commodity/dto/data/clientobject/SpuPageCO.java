package org.dromara.merchant.client.commodity.dto.data.clientobject;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description spu后台分页数据CO
 * @Author Code Skywalker
 * @Date 2025/12/11 15:44
 */
@Data
public class SpuPageCO {

    /**
     * id
     */
    private Long id;

    /**
     * 图片
     */
    private String image;

    /**
     * 名称
     */
    private String name;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 价格
     */
    private String price;

    /**
     * 是否上架
     */
    private Boolean isMarketable;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
