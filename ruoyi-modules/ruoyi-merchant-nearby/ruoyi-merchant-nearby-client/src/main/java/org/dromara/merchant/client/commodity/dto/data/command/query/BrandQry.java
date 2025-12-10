package org.dromara.merchant.client.commodity.dto.data.command.query;

import lombok.Data;

/**
 * @Description 品牌条件查询参数
 * @Author Code Skywalker
 * @Date 2025/12/10 14:13
 */
@Data
public class BrandQry {

    /**
     * 分类Id
     */
    private Integer categoryId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌的首字母
     */
    private String letter;

}
