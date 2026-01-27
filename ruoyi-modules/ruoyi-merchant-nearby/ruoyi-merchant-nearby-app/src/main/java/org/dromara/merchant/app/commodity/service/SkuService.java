package org.dromara.merchant.app.commodity.service;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.commodity.ISkuService;
import org.dromara.merchant.app.commodity.executor.SkuCreateExe;
import org.dromara.merchant.app.commodity.executor.SkuModifyExe;
import org.dromara.merchant.client.commodity.dto.data.command.SkuCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.SkuGenCmd;
import org.dromara.merchant.client.commodity.dto.data.command.SkuModifyCmd;
import org.dromara.merchant.domain.commodity.gateway.ISkuGateway;
import org.dromara.merchant.domain.commodity.model.Sku;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/9 17:28
 */
@Component
@RequiredArgsConstructor
public class SkuService implements ISkuService {

    private final SkuCreateExe skuCreateExe;
    private final SkuModifyExe skuModifyExe;
    private final ISkuGateway skuGateway;


    @Override
    public boolean create(List<SkuCreateCmd> cmd) {
        return this.skuCreateExe.execute(cmd);
    }

    @Override
    public boolean modify(List<SkuModifyCmd> cmd) {
        return this.skuModifyExe.execute(cmd);
    }

    @Override
    public boolean deleteBySpuId(Long spuId) {
        return this.skuGateway.deleteBySpuId(spuId);
    }

    @Override
    public Sku queryById(Long id) {
        return this.skuGateway.queryById(id);
    }

    /**
     * 根据skuIds查询商品sku
     *
     * @param skuIds skuIds
     * @return 商品sku
     */
    @Override
    public List<Sku> queryBySkuIds(Collection<Long> skuIds) {
        return this.skuGateway.queryBySkuIds(skuIds);
    }

    @Override
    public List<Sku> queryBySpuId(Long spuId) {
        return this.skuGateway.queryBySpuId(spuId);
    }

    @Override
    public List<SkuCreateCmd> generateSkus(Long spuId, Map<String, List<String>> specItems, SkuGenCmd.SpuInfo baseSku) {
        List<SkuCreateCmd> skuList = new ArrayList<>();

        // 如果属性为空，则根据baseInfo创建sku
        if (specItems == null || specItems.isEmpty()) {
            SkuCreateCmd sku = new SkuCreateCmd();
            BeanUtils.copyProperties(baseSku, sku);
            sku.setSpuId(spuId);
            sku.setSpec("{}");
            skuList.add(sku);
            return skuList;
        }

        // 获取所有规格项的键
        List<String> specKeys = new ArrayList<>(specItems.keySet());

        // 生成所有规格组合
        List<List<String>> combinations = generateCombinations(new ArrayList<>(specItems.values()));

        // 为每个组合创建SKU
        for (List<String> combination : combinations) {
            // 复制基础SKU信息
            SkuCreateCmd sku = new SkuCreateCmd();
            BeanUtils.copyProperties(baseSku, sku);

            // 设置SPU ID
            sku.setSpuId(spuId);

            // 构造规格描述（JSON格式）
            Map<String, String> specMap = new HashMap<>();
            StringBuilder nameBuilder = new StringBuilder(baseSku.getName() != null ? baseSku.getName() : "商品");

            for (int j = 0; j < specKeys.size(); j++) {
                specMap.put(specKeys.get(j), combination.get(j));
                nameBuilder.append(" ").append(combination.get(j));
            }

            // 将规格Map转换为JSON字符串
            String specJson = JSON.toJSONString(specMap);

            // 设置规格和名称
            sku.setSpec(specJson);
            sku.setName(nameBuilder.toString());

            skuList.add(sku);
        }

        return skuList;
    }

    /**
     * 生成所有可能的组合
     * @param lists 规格选项列表
     * @return 所有可能的组合
     */
    private List<List<String>> generateCombinations(List<List<String>> lists) {
        List<List<String>> result = new ArrayList<>();
        if (lists.isEmpty()) {
            return result;
        }

        generateCombinationsRecursive(lists, result, 0, new ArrayList<>());
        return result;
    }

    /**
     * 递归生成组合
     * @param lists 规格选项列表
     * @param result 结果集合
     * @param depth 当前深度
     * @param current 当前组合
     */
    private void generateCombinationsRecursive(List<List<String>> lists, List<List<String>> result,
                                              int depth, List<String> current) {
        // 如果已经处理完所有规格项，则添加到结果中
        if (depth == lists.size()) {
            result.add(new ArrayList<>(current));
            return;
        }

        // 遍历当前规格项的所有选项
        for (String item : lists.get(depth)) {
            current.add(item);
            generateCombinationsRecursive(lists, result, depth + 1, current);
            current.remove(current.size() - 1); // 回溯
        }
    }

    /**
     * 复制基础SKU属性
     * @param source 源SKU
     * @param target 目标SKU
     */
    private void copyBaseSkuProperties(SkuCreateCmd source, SkuCreateCmd target) {
        target.setSn(source.getSn());
        target.setPrice(source.getPrice());
        target.setMemberPrice(source.getMemberPrice());
        target.setVipPrice(source.getVipPrice());
        target.setOriginalPrice(source.getOriginalPrice());
        target.setNum(source.getNum());
        target.setAlertNum(source.getAlertNum());
        target.setImage(source.getImage());
        target.setImages(source.getImages());
        target.setWeight(source.getWeight());
        target.setVolume(source.getVolume());
        target.setMinPurchase(source.getMinPurchase());
        target.setMaxPurchase(source.getMaxPurchase());
        target.setCategoryId(source.getCategoryId());
        target.setCategoryName(source.getCategoryName());
        target.setBrandName(source.getBrandName());

    }
}
