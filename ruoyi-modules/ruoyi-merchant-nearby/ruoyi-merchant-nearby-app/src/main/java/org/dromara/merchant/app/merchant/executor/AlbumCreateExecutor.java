package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.merchant.client.merchant.dto.data.command.AlbumCreateCmd;
import org.dromara.merchant.domain.merchant.gateway.IAlbumGateway;
import org.dromara.merchant.infrastructure.merchant.converter.AlbumConverter;
import org.springframework.stereotype.Component;

/**
 * @Description 相册创建执行器
 * @Author Code Skywalker
 * @Date 2025/12/17 16:40
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AlbumCreateExecutor {

    private final IAlbumGateway albumGateway;
    private final AlbumConverter albumConverter;

    /**
     * 执行相册创建操作
     *
     * @param cmd 相册创建命令
     * @return 是否创建成功
     */
    public boolean execute(AlbumCreateCmd cmd) {
        return albumGateway.save(albumConverter.toEntity(cmd));
    }
}
