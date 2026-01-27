package org.dromara.express.infrastructure.convert;

import org.dromara.express.client.dto.data.command.ConfigCreateCmd;
import org.dromara.express.client.dto.data.command.ConfigModifyCmd;
import org.dromara.express.domain.model.Config;
import org.dromara.express.infrastructure.mapper.dataobject.ConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;


/**
 * 快递数据转换器
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConfigConverter {

    ConfigDO toDO(Config config);

    Config toEntity(ConfigDO configDO);

    Config toEntity(ConfigCreateCmd cmd);

    Config toEntity(ConfigModifyCmd cmd);

}
