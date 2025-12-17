package org.dromara.merchant.infrastructure.merchant.mapper.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.mybatis.core.domain.BaseEntity;


/**
 * @Description 相册DO对象
 * @Author Code Skywalker
 * @Date 2025/12/17 15:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("tb_album")
public class AlbumDO extends BaseEntity {

    /**
     * 相册ID
     */
    @TableId
    private Long id;

    /**
     * 文件类型: 0文件夹 1文件
     */
    private String type;

    /**
     * 名称
     */
    private String name;

    /**
     * 父级Id
     */
    private Long parentId;

    /**
     * 商家Id
     */
    private Long merchantId;

    /**
     * 租户编号
     */
    private String tenantId;

    /**
     * 文件URL地址
     */
    private String url;

    /**
     * 文件大小(字节)
     */
    private Long fileSize;

    /**
     * 文件后缀名
     */
    private String fileSuffix;

    /**
     * 关联的系统文件ID
     */
    private Long ossId;

}
