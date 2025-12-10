package org.dromara.merchant.client.commodity.dto.data.clientobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.dromara.common.core.utils.TreeNode;

import java.util.List;

/**
 * @Description 分类树
 * @Author Code Skywalker
 * @Date 2025/12/10 14:36
 */
@Data
public class CategoryTreeCO implements TreeNode<Integer, CategoryTreeCO> {

    /**
     * 分类ID
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 是否显示
     */
    private String isShow;

    /**
     * 是否导航
     */
    private String isMenu;

    /**
     * 排序
     */
    private Integer seq;

    /**
     * 上级ID
     */
    private Integer parentId;

    /**
     * 子级
     */
    private List<CategoryTreeCO> children;


    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public Integer getParentId() {
        return this.parentId;
    }

    @Override
    @JsonIgnore
    public Integer getWeight() {
        return this.seq;
    }

    @Override
    @JsonIgnore
    public boolean isRoot() {
        return this.parentId.equals(0);
    }

    @Override
    @JsonIgnore
    public boolean isLeaf() {
        return this.children == null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setChildren(List<? extends TreeNode<Integer, CategoryTreeCO>> leaf) {
        this.children = (List<CategoryTreeCO>) leaf;
    }

    @Override
    public int compareTo(CategoryTreeCO o) {
        return this.seq.compareTo(o.getSeq());
    }
}
