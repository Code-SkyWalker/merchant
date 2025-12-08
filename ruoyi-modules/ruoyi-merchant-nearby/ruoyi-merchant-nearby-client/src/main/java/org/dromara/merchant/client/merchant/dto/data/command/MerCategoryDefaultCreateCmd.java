package org.dromara.merchant.client.merchant.dto.data.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.merchant.client.Command;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025-11-03 14:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerCategoryDefaultCreateCmd implements Command {
    private String categoryName;
    private String categoryImage;
    private Integer categorySort;
    private Integer state;
}
