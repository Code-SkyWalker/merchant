package org.dromara.merchant.infrastructure.merchant.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.merchant.gateway.IAlbumGateway;
import org.dromara.merchant.domain.merchant.model.Album;
import org.dromara.merchant.infrastructure.merchant.converter.AlbumConverter;
import org.dromara.merchant.infrastructure.merchant.mapper.AlbumMapper;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.AlbumDO;
import org.springframework.stereotype.Component;

/**
 * @Description 相册网关实现
 * @Author Code Skywalker
 * @Date 2025/12/17 16:12
 */
@Component
@RequiredArgsConstructor
public class AlbumGateway implements IAlbumGateway {

    private final AlbumMapper mapper;
    private final AlbumConverter converter;

    @Override
    public boolean save(Album album) {
        return this.mapper.insertOrUpdate(converter.toDO(album));
    }

    @Override
    public boolean delete(Long id) {
        return this.mapper.deleteById(id) > 0;
    }

    @Override
    public Album findById(Long id) {
        AlbumDO albumDO = this.mapper.selectById(id);
        return this.converter.toEntity(albumDO);
    }
}
