package org.dromara.merchant.app.freight;

import org.dromara.merchant.client.freight.dto.data.command.FreightConfigCreateCmd;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/4 17:42
 */
public interface IFreightConfigService {

    boolean create(FreightConfigCreateCmd cmd);
}
