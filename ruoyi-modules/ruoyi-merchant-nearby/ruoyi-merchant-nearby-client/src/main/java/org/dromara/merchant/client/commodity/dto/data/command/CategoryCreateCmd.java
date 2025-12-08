package org.dromara.merchant.client.commodity.dto.data.command;

import lombok.Data;

/**
 * @Description 分类修改参数
 * @Author Code Skywalker
 * @Date 2025/12/8 15:38
 */
@Data
public class CategoryCreateCmd {

    /**
     * 分类名称
     */
    private String name;

    /**
     * 商品数量
     */
    private Integer goodsNum;

    /**
     * 是否显示
     */
    private String isShow;

    /**
     * 是否导航
     */
    private String isMenu;

    /**
     * 排序
     */
    private Integer seq;

    /**
     * 上级ID
     */
    private Integer parentId;

    /**
     * 商家Id
     */
    private Long merchantId;

}
