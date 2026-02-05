package org.dromara.huifu.app.payment.executor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

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

    private Long goods_id;
    private String goods_name;
    private BigDecimal price;
    private Integer quantity;
}
