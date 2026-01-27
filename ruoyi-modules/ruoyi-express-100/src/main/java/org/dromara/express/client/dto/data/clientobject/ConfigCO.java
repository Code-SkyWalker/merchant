package org.dromara.express.client.dto.data.clientobject;

import lombok.Data;

/**
 * @Description 快递100配置对象
 * @Author Code Skywalker
 * @Date 2026/1/22 14:06
 */
@Data
public class ConfigCO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 授权码
     */
    private String customer;

    /**
     * 授权key
     */
    private String key;

    /**
     * 授权secret
     */
    private String secret;

    /**
     * 是否启用：0-禁用，1-启用
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

}
