package org.dromara.express.client.dto.data.command;

import lombok.Data;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/1/22 14:07
 */
@Data
public class ConfigModifyCmd {

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
     * 授权key
     */
    private String secret;

    /**
     * 备注
     */
    private String remark;

}
