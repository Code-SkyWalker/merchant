package org.dromara.huifu.infrastructure.convertor;

import com.huifu.bspay.sdk.opps.core.config.MerConfig;
import org.dromara.huifu.client.config.dto.cmd.HuifuConfigCreateCmd;
import org.dromara.huifu.client.config.dto.cmd.HuifuConfigModifyCmd;
import org.dromara.huifu.domain.model.HuifuConfig;
import org.dromara.huifu.infrastructure.mapper.dataobject.HuifuConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/13 17:09
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HuifuConfigConvertor {

    HuifuConfigDO toDO(HuifuConfig huifuConfig);

    HuifuConfig toEntity(HuifuConfigDO huifuConfigDO);

    HuifuConfig toEntity(HuifuConfigCreateCmd cmd);

    HuifuConfig toEntity(HuifuConfigModifyCmd cmd);

    @Mapping(source = "privateKey",target = "rsaPrivateKey")
    @Mapping(source = "publicKey",target = "rsaPublicKey")
    @Mapping(source = "productId",target = "procutId")
    @Mapping(source = "huifuId",target = "sysId")
    MerConfig toConfig(HuifuConfigCreateCmd cmd);

    @Mapping(source = "privateKey",target = "rsaPrivateKey")
    @Mapping(source = "publicKey",target = "rsaPublicKey")
    @Mapping(source = "productId",target = "procutId")
    @Mapping(source = "huifuId",target = "sysId")
    MerConfig toConfig(HuifuConfigModifyCmd cmd);

    @Mapping(source = "privateKey",target = "rsaPrivateKey")
    @Mapping(source = "publicKey",target = "rsaPublicKey")
    @Mapping(source = "productId",target = "procutId")
    @Mapping(source = "huifuId",target = "sysId")
    MerConfig toConfig(HuifuConfig config);

}
