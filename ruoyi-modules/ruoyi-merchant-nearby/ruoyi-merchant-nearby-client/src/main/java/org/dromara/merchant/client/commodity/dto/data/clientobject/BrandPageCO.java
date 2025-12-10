package org.dromara.merchant.client.commodity.dto.data.clientobject;

import lombok.Data;

/**
 * @Description 品牌分页查询数据CO
 * @Author Code Skywalker
 * @Date 2025/12/10 14:24
 */
@Data
public class BrandPageCO {

    /**
     * 品牌id
     */
    private Integer id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌图片地址
     */
    private String image;

    /**
     * 品牌的首字母
     */
    private String letter;

    /**
     * 排序
     */
    private Integer seq;

    /**
     * 商家Id
     */
    private Long merchantId;

}
