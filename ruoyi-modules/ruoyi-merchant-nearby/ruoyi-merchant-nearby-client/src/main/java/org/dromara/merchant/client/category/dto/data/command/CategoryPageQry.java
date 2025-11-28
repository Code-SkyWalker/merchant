package org.dromara.merchant.client.category.dto.data.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025-11-03 14:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPageQry {

    private static final long serialVersionUID = 7360004986109603599L;

    /** 租户id */
    private String tenantId;
    /** 状态 */
    private Integer state;
    /** 分类名称 */
    private String categoryName;
    /** 父级Id */
    private Long parentId;
}
