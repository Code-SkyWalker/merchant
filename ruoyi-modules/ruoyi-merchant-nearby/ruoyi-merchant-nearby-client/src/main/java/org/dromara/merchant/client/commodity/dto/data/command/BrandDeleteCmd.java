package org.dromara.merchant.client.commodity.dto.data.command;

import lombok.Data;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/8 13:03
 */
@Data
public class BrandDeleteCmd {

    /**
     * 品牌Id
     */
    private Integer id;

    /**
     * 分类Id
     */
    private Integer categoryId;
}
