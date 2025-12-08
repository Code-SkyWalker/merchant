package org.dromara.merchant.client.merchant.dto.data.clientobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025-11-03 15:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerCategoryCO {

    /**
     * 分类主键
     */
    private Long categoryId;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 分类图片
     */
    private String categoryImage;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类排序
     */
    private Integer categorySort;

    /**
     * 0:禁用 1:启用
     */
    private Integer state;

    /**
     * 租户编号
     */
    private String tenantId;
}
