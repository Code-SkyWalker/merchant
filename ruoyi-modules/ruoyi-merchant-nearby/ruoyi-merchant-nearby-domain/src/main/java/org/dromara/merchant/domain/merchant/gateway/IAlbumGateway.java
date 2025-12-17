package org.dromara.merchant.domain.merchant.gateway;

import org.dromara.merchant.domain.merchant.model.Album;

/**
 * @Description 相册网关接口
 * @Author Code Skywalker
 * @Date 2025/12/17 16:03
 */
public interface IAlbumGateway {

    /**
     * 保存相册
     * @param album 相册
     * @return 是否保存成功
     */
    boolean save(Album album);

    /**
     * 删除相册
     * @param id 相册ID
     * @return 是否删除成功
     */
    boolean delete(Long id);

    /**
     * 根据ID查询相册
     * @param id 相册ID
     * @return 相册
     */
    Album findById(Long id);

}
