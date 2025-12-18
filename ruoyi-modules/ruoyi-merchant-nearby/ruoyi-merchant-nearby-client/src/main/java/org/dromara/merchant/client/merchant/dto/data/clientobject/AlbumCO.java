package org.dromara.merchant.client.merchant.dto.data.clientobject;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description 相册客户端对象
 * @Author Code Skywalker
 * @Date 2025/12/17 15:50
 */
@Data
public class AlbumCO implements Serializable {


    @Serial
    private static final long serialVersionUID = -2400888538717147739L;

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
     * OSS文件ID
     */
    private Long ossId;

    /**
     * 文件是否删除 true 已删除 false 未删除
     */
    private Boolean deleted = false;

}
