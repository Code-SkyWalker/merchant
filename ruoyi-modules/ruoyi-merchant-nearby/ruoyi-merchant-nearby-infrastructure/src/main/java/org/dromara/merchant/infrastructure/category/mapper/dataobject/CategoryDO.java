package org.dromara.merchant.infrastructure.category.mapper.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025-11-03 14:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryDO extends CategoryDefaultDO {

    @Serial
    private static final long serialVersionUID = -445031827531286352L;
    /**
     * 租户id
     */
    private String tenantId;
}
