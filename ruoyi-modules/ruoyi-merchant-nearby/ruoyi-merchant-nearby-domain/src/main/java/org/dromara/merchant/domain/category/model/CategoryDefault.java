package org.dromara.merchant.domain.category.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * 平台默认分类表
 */
@Data
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDefault {
    /**
     * 分类主键
     */
    private Long categoryId;

    /**
     * 上级id
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
    private CategoryState state;


    /**
     * 分类状态
     */
    @Getter
    public enum CategoryState {

        /** 禁用 */
        DISABLED(0),

        /** 启用 */
        ENABLED(1);

        /** 状态值 */
        private final Integer value;

        CategoryState(Integer value) {
            this.value = value;
        }
    }
}
