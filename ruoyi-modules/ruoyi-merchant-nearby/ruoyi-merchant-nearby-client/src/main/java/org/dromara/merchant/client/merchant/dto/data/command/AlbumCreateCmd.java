package org.dromara.merchant.client.merchant.dto.data.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description 相册创建命令对象
 * @Author Code Skywalker
 * @Date 2025/12/17 15:55
 */
@Data
public class AlbumCreateCmd implements Serializable {


    @Serial
    private static final long serialVersionUID = -854585713210278842L;


    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 父级Id
     */
    private Long parentId;

    /**
     * 商家Id
     */
    @NotNull(message = "商家Id不能为空")
    private Long merchantId;

}
