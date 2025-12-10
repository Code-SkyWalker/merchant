package org.dromara.common.core.utils;

import java.io.Serializable;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description 树型化工具类
 * @Author Code Skywalker
 * @Date 2023/7/21 14:43
 */
public class TreeIfyUtils {

    /**
     * 树型化算法
     *
     * @param list        原数据库查询的扁平化数据结构
     * @param root        根节点
     * @param parentCheck 检查是否是根节点
     * @param children    叶子节点
     * @param <E>         具体的实体类型
     * @return 树
     */
    public static <E extends TreeNode<? extends Serializable, E>> List<E> treeIfy(List<E> list, Predicate<E> root, BiFunction<E, E, Boolean> parentCheck, BiConsumer<E, List<E>> children) {
        Stream<E> parent = list.stream()
                .filter(root)
                .peek(current -> children.accept(current, makeChildren(current, list, parentCheck, children)));
        return parent
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * 筛选叶子节点
     *
     * @param parent      父节点
     * @param allData     所有数据
     * @param parentCheck 根节点检查
     * @param children    筛选之后的叶子节点
     * @param <E>         /
     * @return 叶子节点
     */
    private static <E extends TreeNode<? extends Serializable, E>> List<E> makeChildren(E parent, List<E> allData, BiFunction<E, E, Boolean> parentCheck, BiConsumer<E, List<E>> children) {
        return allData.stream()
                .filter(current -> parentCheck.apply(parent, current))
                .peek(current -> children.accept(current, makeChildren(current, allData, parentCheck, children)))
                .sorted()
                .collect(Collectors.toList());
    }

}
