package org.dromara.merchant.client.commodity.dto.data.command;

import lombok.Data;

import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/8 13:03
 */
@Data
public class BrandCreateCmd {

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

    /**
     * 分类Id
     */
    private List<Integer> categoryIds;
}
