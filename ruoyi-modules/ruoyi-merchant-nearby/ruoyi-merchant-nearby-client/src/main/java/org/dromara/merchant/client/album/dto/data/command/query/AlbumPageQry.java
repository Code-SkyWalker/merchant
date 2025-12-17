package org.dromara.merchant.client.album.dto.data.command.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description 相册分页查询对象
 * @Author Code Skywalker
 * @Date 2025/12/17 16:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AlbumPageQry implements Serializable {

    @Serial
    private static final long serialVersionUID = -1867461542556596020L;

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
}
