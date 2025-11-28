package org.dromara.merchant.client.category.dto.data.command;

import com.alibaba.cola.dto.PageQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025-11-03 14:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DefaultCategoryPageQry extends PageQuery {

    private static final long serialVersionUID = 7360004986109603599L;

    private Integer state;
    private String categoryName;
    private Long parentId;
}
