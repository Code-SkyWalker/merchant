package org.dromara.express.infrastructure.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.express.domain.gateway.IConfigGateway;
import org.dromara.express.domain.model.Config;
import org.dromara.express.infrastructure.convert.ConfigConverter;
import org.dromara.express.infrastructure.mapper.ConfigMapper;
import org.springframework.stereotype.Component;

/**
 * 快递配置仓储实现
 */
@Component
@RequiredArgsConstructor
public class ConfigGateway implements IConfigGateway {

    private final ConfigMapper mapper;
    private final ConfigConverter converter;

    @Override
    public boolean save(Config config) {
        return this.mapper.insertOrUpdate(this.converter.toDO(config));
    }

    @Override
    public boolean deleteById(Long id) {
        return this.mapper.deleteById(id) > 0;
    }

    @Override
    public Config queryById(Long id) {
        return this.converter.toEntity(this.mapper.selectById(id));
    }

}
