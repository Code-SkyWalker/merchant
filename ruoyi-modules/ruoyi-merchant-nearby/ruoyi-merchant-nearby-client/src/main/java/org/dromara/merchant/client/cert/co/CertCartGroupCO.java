package org.dromara.merchant.client.cert.co;

import lombok.Data;

import java.util.List;

/**
 * 认证购物车分组CO
 * 按商家和优惠活动进行分组
 *
 * @author Code Skywalker
 */
@Data
public class CertCartGroupCO {

    /**
     * 商家信息
     */
    private MerchantInfoCO merchantInfo;

    /**
     * 商品列表
     */
    private List<ProductInfoCO> products;
}
