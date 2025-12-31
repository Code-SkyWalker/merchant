package org.dromara.merchant.client.freight.dto.data.clientobject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.merchant.client.freight.dto.data.command.DeliveryConfig;

import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/31 13:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FreightConfigExpressCO extends FreightConfigCO {

    /**
     * 快递配送模板
     */
    private List<ExpressTemplateCO> expressTemplates;
}
