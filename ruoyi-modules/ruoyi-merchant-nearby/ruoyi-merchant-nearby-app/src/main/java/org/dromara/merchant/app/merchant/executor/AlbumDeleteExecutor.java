package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.merchant.app.merchant.service.AlbumService;
import org.dromara.merchant.domain.merchant.gateway.IAlbumGateway;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 相册删除执行器
 * @Author Code Skywalker
 * @Date 2025/12/17 16:45
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AlbumDeleteExecutor {

    private final IAlbumGateway albumGateway;

    /**
     * 执行相册删除操作
     *
     * @param albumId 相册ID
     * @return 是否删除成功
     */
    public boolean execute(Long albumId) {
        return albumGateway.delete(albumId);
    }

    /**
     * 执行相册批量删除操作
     *
     * @param albumIds 相册ID列表
     * @return 是否删除成功
     */
    public boolean execute(List<Long> albumIds) {
        return albumGateway.deleteByIds(albumIds);
    }
}
