package org.dromara.merchant.client.commodity.dto.data.command;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Description sku生成参数
 * @Author Code Skywalker
 * @Date 2025/12/11 10:00
 */
@Data
public class SkuGenCmd {

    /**
     * 商品spuID
     */
    private Long spuId;

    /**
     * 商品规格选项列表
     */
    @NotNull
    private Map<String, List<String>> specItems;

    /**
     * 基础sku
     */
    @NotNull
    private SpuInfo baseSku;

    /**
     * 商品spu信息
     */
    @Data
    public static class SpuInfo {

        /**
         * 商品名称
         */
        @NotNull
        private String name;

        /**
         * 商品分类ID
         */
        @NotNull
        private Integer categoryId;

        /**
         * 商品分类名称
         */
        @NotNull
        private String categoryName;

        /**
         * 商品品牌ID
         */
        private Integer brandId;

        /**
         * 商品品牌名称
         */
        private String brandName;

    }
}
