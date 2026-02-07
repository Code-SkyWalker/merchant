package org.dromara.merchant.app.freight.executor.query;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.freight.dto.data.clientobject.ExpressTemplateCO;
import org.dromara.merchant.client.freight.dto.data.clientobject.FreightConfigCO;
import org.dromara.merchant.client.freight.dto.data.clientobject.FreightConfigExpressCO;
import org.dromara.merchant.domain.freight.model.DeliveryMethod;
import org.dromara.merchant.infrastructure.freight.mapper.ExpressTemplateMapper;
import org.dromara.merchant.infrastructure.freight.mapper.FreightConfigMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.baomidou.mybatisplus.core.toolkit.StringPool.COMMA;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/31 15:46
 */
@Component
@RequiredArgsConstructor
public class FreightConfigQryExe {

    private final FreightConfigMapper configMapper;
    private final ExpressTemplateMapper expressTemplateMapper;


    public FreightConfigCO execute(Long merchantId, DeliveryMethod deliveryMethod) {

        // 查询基本配置
        FreightConfigCO co = configMapper.selectCOByMerchantId(merchantId, deliveryMethod.getCode());

        if (DeliveryMethod.EXPRESS_DELIVERY.equals(deliveryMethod)) {
            return executeExpress(co);
        }

        return co;
    }

    /**
     * 运费配置-物流配送
     *
     * @param co 基本配置
     */
    private FreightConfigCO executeExpress(FreightConfigCO co) {
        if (co == null) return null;
        var configExpressCO = new FreightConfigExpressCO();
        BeanUtil.copyProperties(co, configExpressCO);

        if (!StringUtils.hasText(co.getRelationId())) return co;
        String[] expressTemplateIds = co.getRelationId().split(COMMA);
        List<ExpressTemplateCO> expressTemplateCOs = this.expressTemplateMapper.selectByTemplateIds(expressTemplateIds);

        configExpressCO.setExpressTemplates(expressTemplateCOs);
        return configExpressCO;
    }


}
