package org.dromara.merchant.infrastructure.commodity.mapper.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 商品类目
 */
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_category")
public class CategoryDO extends BaseEntity {

    /**
     * 分类ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 商品数量
     */
    private Integer goodsNum;

    /**
     * 是否显示
     */
    private Integer isShow;

    /**
     * 是否导航
     */
    private Integer isMenu;

    /**
     * 排序
     */
    private Integer seq;

    /**
     * 上级ID
     */
    private Integer parentId;

    /**
     * 商家Id
     */
    private Long merchantId;

    /**
     * 租户编号
     */
    private String tenantId;
}
