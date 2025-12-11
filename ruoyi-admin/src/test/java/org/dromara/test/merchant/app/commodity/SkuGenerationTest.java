package org.dromara.test.merchant.app.commodity;

import com.alibaba.fastjson.JSON;
import org.dromara.merchant.app.commodity.service.SkuService;
import org.dromara.merchant.client.commodity.dto.data.command.SkuCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.SkuGenCmd;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SkuGenerationTest {

    @Test
    public void testGenerateSkus() {
        // 创建SkuService实例
        SkuService skuService = new SkuService(null, null, null);

        // 准备测试数据
        Long spuId = 1L;

        // 创建规格项
        Map<String, List<String>> specItems = new HashMap<>();
        specItems.put("颜色", Arrays.asList("红色", "蓝色"));
        specItems.put("尺寸", Arrays.asList("S", "M", "L"));

        // 创建基础SKU信息
        SkuGenCmd.SpuInfo baseSku = new SkuGenCmd.SpuInfo();
        baseSku.setName("测试商品");
        baseSku.setCategoryId(1);
        baseSku.setCategoryName("测试分类");
        baseSku.setCategoryId(1);
        baseSku.setBrandName("测试品牌");

        // 调用生成SKU方法
        List<SkuCreateCmd> generatedSkus = skuService.generateSkus(spuId, specItems, baseSku);

        // 验证结果
        assertNotNull(generatedSkus);
        assertEquals(6, generatedSkus.size()); // 2种颜色 × 3种尺寸 = 6个SKU

        // 验证每个SKU的信息
        for (SkuCreateCmd sku : generatedSkus) {
            assertEquals(spuId, sku.getSpuId());
            assertEquals(baseSku.getCategoryId(), sku.getCategoryId());
            assertEquals(baseSku.getCategoryName(), sku.getCategoryName());
            assertEquals(baseSku.getBrandName(), sku.getBrandName());
            assertNotNull(sku.getName());
            assertNotNull(sku.getSpec());
        }

        // 验证具体的SKU组合（JSON格式）
        Set<String> expectedSpecs = new HashSet<>();
        expectedSpecs.add("{\"颜色\":\"红色\",\"尺寸\":\"S\"}");
        expectedSpecs.add("{\"颜色\":\"红色\",\"尺寸\":\"M\"}");
        expectedSpecs.add("{\"颜色\":\"红色\",\"尺寸\":\"L\"}");
        expectedSpecs.add("{\"颜色\":\"蓝色\",\"尺寸\":\"S\"}");
        expectedSpecs.add("{\"颜色\":\"蓝色\",\"尺寸\":\"M\"}");
        expectedSpecs.add("{\"颜色\":\"蓝色\",\"尺寸\":\"L\"}");

        Set<String> actualSpecs = new HashSet<>();
        Set<String> actualNames = new HashSet<>();

        for (SkuCreateCmd sku : generatedSkus) {
            actualSpecs.add(sku.getSpec());
            actualNames.add(sku.getName());
        }

        assertEquals(expectedSpecs, actualSpecs);

        // 验证名称生成
        assertTrue(actualNames.contains("测试商品 红色 S"));
        assertTrue(actualNames.contains("测试商品 红色 M"));
        assertTrue(actualNames.contains("测试商品 红色 L"));
        assertTrue(actualNames.contains("测试商品 蓝色 S"));
        assertTrue(actualNames.contains("测试商品 蓝色 M"));
        assertTrue(actualNames.contains("测试商品 蓝色 L"));

        System.out.println(JSON.toJSONString(generatedSkus));
    }

    @Test
    public void testGenerateSkusWithSingleSpec() {
        // 创建SkuService实例
        SkuService skuService = new SkuService(null, null, null);

        // 准备测试数据
        Long spuId = 2L;

        // 创建规格项（只有一种规格）
        Map<String, List<String>> specItems = new HashMap<>();
        specItems.put("颜色", Arrays.asList("红色", "蓝色", "绿色"));

        // 创建基础SKU信息
        SkuGenCmd.SpuInfo baseSku = new SkuGenCmd.SpuInfo();
        baseSku.setName("单规格商品");
        baseSku.setCategoryId(2);
        baseSku.setCategoryName("服装");


        // 调用生成SKU方法
        List<SkuCreateCmd> generatedSkus = skuService.generateSkus(spuId, specItems, baseSku);

        // 验证结果
        assertNotNull(generatedSkus);
        assertEquals(3, generatedSkus.size()); // 3种颜色 = 3个SKU

        // 验证每个SKU的信息
        Set<String> expectedSpecs = new HashSet<>();
        expectedSpecs.add("{\"颜色\":\"红色\"}");
        expectedSpecs.add("{\"颜色\":\"蓝色\"}");
        expectedSpecs.add("{\"颜色\":\"绿色\"}");

        Set<String> actualSpecs = new HashSet<>();
        Set<String> actualNames = new HashSet<>();

        for (SkuCreateCmd sku : generatedSkus) {
            actualSpecs.add(sku.getSpec());
            actualNames.add(sku.getName());
            assertEquals(spuId, sku.getSpuId());
        }

        assertEquals(expectedSpecs, actualSpecs);
        assertTrue(actualNames.contains("单规格商品 红色"));
        assertTrue(actualNames.contains("单规格商品 蓝色"));
        assertTrue(actualNames.contains("单规格商品 绿色"));

        System.out.println(JSON.toJSONString(generatedSkus));

    }

    @Test
    public void testGenerateSkusWithEmptySpec() {
        // 创建SkuService实例
        SkuService skuService = new SkuService(null, null, null);

        // 准备测试数据
        Long spuId = 3L;

        // 创建空的规格项
        Map<String, List<String>> specItems = new HashMap<>();

        // 创建基础SKU信息
        SkuGenCmd.SpuInfo baseSku = new SkuGenCmd.SpuInfo();
        baseSku.setName("无规格商品");
        baseSku.setCategoryId(3);
        baseSku.setCategoryName("配件");
        baseSku.setBrandName("品牌B");

        // 调用生成SKU方法
        List<SkuCreateCmd> generatedSkus = skuService.generateSkus(spuId, specItems, baseSku);

        // 验证结果
        assertNotNull(generatedSkus);
        assertEquals(0, generatedSkus.size()); // 没有规格项，应该生成0个SKU

        System.out.println(JSON.toJSONString(generatedSkus));

    }
}
