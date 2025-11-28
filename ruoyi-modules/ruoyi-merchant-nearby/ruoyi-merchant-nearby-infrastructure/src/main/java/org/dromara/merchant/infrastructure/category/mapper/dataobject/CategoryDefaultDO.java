package org.dromara.merchant.infrastructure.category.mapper.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025-10-23 15:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryDefaultDO extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 7284315347430498635L;
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
    private Integer state;

}
