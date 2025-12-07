package org.dromara.merchant.domain.commodity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 商品分类
 * @Author Code Skywalker
 * @Date 2025/12/8 15:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommCategory {

    /**
     * 分类ID
     */
    private Integer id;

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
