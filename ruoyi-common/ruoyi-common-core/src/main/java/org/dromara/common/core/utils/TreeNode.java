package org.dromara.common.core.utils;

import java.util.List;

/**
 * @Description 树形节点 接口, 需要实现此接口并调用TreeIfyUtils.treeIfy()方法
 * @Author Code Skywalker
 * @Date 2023/7/21 14:49
 */
public interface TreeNode<T, E> extends Comparable<E> {

    /**
     * 获取id
     * @return  /
     */
    T getId();

    /**
     * 获取上级id
     * @return  /
     */
    T getParentId();

    /**
     * 获取权重(排序)
     * @return  /
     */
    T getWeight();

    /**
     * 是否是根节点
     * @return  /
     */
    boolean isRoot();

    /**
     * 是否是根节点
     * @return  /
     */
    boolean isLeaf();

    /**
     * 设置叶子节点
     * @param leaf  /
     */
    void setChildren(List<? extends TreeNode<T, E>> leaf);
}


