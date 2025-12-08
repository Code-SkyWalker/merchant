package org.dromara.merchant.client.commodity.dto.data.command;

import lombok.Data;

import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/8 13:03
 */
@Data
public class BrandModifyCmd {

    /**
     * 品牌Id
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

}
