package org.dromara.huifu.app.payment.executor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/12 15:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CommodityDetail {

    private String goods_id;
    private String goods_name;
    private String price;
    private String quantity;
}
