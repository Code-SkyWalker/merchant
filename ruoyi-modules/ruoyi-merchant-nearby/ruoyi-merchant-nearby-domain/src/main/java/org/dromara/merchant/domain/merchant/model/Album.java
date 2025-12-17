package org.dromara.merchant.domain.merchant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * @Description 相册领域实体
 * @Author Code Skywalker
 * @Date 2025/12/17 18:00
 */
@Data
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Album {

    public static final String TYPE_FOLDER = "0";
    public static final String TYPE_FILE = "1";


    /**
     * 相册ID
     */
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
