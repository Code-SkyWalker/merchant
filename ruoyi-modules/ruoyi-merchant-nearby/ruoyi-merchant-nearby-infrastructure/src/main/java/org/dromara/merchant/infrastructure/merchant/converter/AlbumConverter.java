package org.dromara.merchant.infrastructure.merchant.converter;

import org.dromara.merchant.client.album.dto.data.command.AlbumModifyCmd;
import org.dromara.merchant.domain.merchant.model.Album;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.dromara.merchant.client.album.dto.data.command.AlbumCreateCmd;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.AlbumDO;

/**
 * @Description 相册转换器
 * @Author Code Skywalker
 * @Date 2025/12/17 16:20
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlbumConverter {

    /**
     * 将AlbumCreateCmd转换为Album
     *
     * @param cmd 创建命令
     * @return AlbumDO
     */
    Album toEntity(AlbumCreateCmd cmd);

    /**
     * 将AlbumModifyCmd转换为Album
     *
     * @param cmd 修改命令
     * @return AlbumDO
     */
    Album toEntity(AlbumModifyCmd cmd);

    /**
     * 将AlbumDO转换为Album
     *
     * @param albumDO 相册DO
     * @return 相册
     */
    Album toEntity(AlbumDO albumDO);

    /**
     * 将Album转换为AlbumDO
     *
     * @param album 相册
     * @return AlbumDO
     */
    AlbumDO toDO(Album album);
}
