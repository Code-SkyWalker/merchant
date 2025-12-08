package org.dromara.merchant.infrastructure.commodity.mapper.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 规格参数模板
 */
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_template")
public class TemplateDO extends BaseEntity {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 规格数量
     */
    private Integer specNum;

    /**
     * 参数数量
     */
    private Integer paraNum;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 分类ID
     */
    private String categoryId;

}
