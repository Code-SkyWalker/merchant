package org.dromara.merchant.client.cert.co;

import lombok.Data;

import java.util.List;

/**
 * 认证购物车汇总CO
 * 包含所有分组信息
 *
 * @author Code Skywalker
 */
@Data
public class CertCartSummaryCO {

    /**
     * 购物车分组列表
     */
    private List<CertCartGroupCO> groups;

    /**
     * 商家数量
     */
    private Integer merchantCount;
}