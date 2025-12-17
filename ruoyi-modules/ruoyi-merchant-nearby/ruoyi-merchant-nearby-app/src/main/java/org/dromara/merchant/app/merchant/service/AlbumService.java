package org.dromara.merchant.app.merchant.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.oss.core.OssClient;
import org.dromara.common.oss.factory.OssFactory;
import org.dromara.merchant.app.merchant.IAlbumService;
import org.dromara.merchant.app.merchant.executor.AlbumCreateExecutor;
import org.dromara.merchant.app.merchant.executor.AlbumDeleteExecutor;
import org.dromara.merchant.app.merchant.executor.AlbumModifyExecutor;
import org.dromara.merchant.client.album.dto.data.clientobject.AlbumCO;
import org.dromara.merchant.client.album.dto.data.command.AlbumCreateCmd;
import org.dromara.merchant.client.album.dto.data.command.AlbumModifyCmd;
import org.dromara.merchant.client.album.dto.data.command.query.AlbumPageQry;
import org.dromara.merchant.domain.merchant.gateway.IAlbumGateway;
import org.dromara.merchant.domain.merchant.model.Album;
import org.dromara.merchant.infrastructure.merchant.mapper.AlbumMapper;
import org.dromara.system.domain.vo.SysOssVo;
import org.dromara.system.service.ISysOssService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description 相册服务实现类
 * @Author Code Skywalker
 * @Date 2025/12/17 16:10
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AlbumService implements IAlbumService {

    private final IAlbumGateway albumGateway;

    private final AlbumCreateExecutor createExecutor;
    private final AlbumModifyExecutor modifyExecutor;
    private final AlbumDeleteExecutor deleteExecutor;

    private final AlbumMapper albumMapper;
    private final ISysOssService ossService;

    @Override
    public boolean create(AlbumCreateCmd cmd) {
        return createExecutor.execute(cmd);
    }

    @Override
    public boolean modify(AlbumModifyCmd cmd) {
        return modifyExecutor.execute(cmd);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean uploadFile(Long merchantId, Long folderId, MultipartFile file) {
        if (file.isEmpty()) return false;

        // 1. 上传文件
        SysOssVo result = ossService.upload(file);

        // 3. 创建相册记录
        Album albumFile = Album.builder()
            .type(Album.TYPE_FILE)   // 文件类型
            .name(file.getOriginalFilename())
            .parentId(folderId)
            .merchantId(merchantId)
            .fileSize(file.getSize())
            .url(result.getUrl())
            .fileSuffix(result.getFileSuffix())
            .ossId(result.getOssId())
            .build();

        return albumGateway.save(albumFile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long albumId) {
        Album album = albumGateway.findById(albumId);

        // 如果是文件类型，需要删除OSS中的文件
        if (Album.TYPE_FILE.equals(album.getType()) && album.getOssId() != null) {
            SysOssVo ossVo = ossService.getById(album.getOssId());
            if (ossVo != null) {
                OssClient ossClient = OssFactory.instance();
                ossClient.delete(ossVo.getUrl());
                ossService.deleteById(ossVo.getOssId());
            }
        }

        return deleteExecutor.execute(albumId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(List<Long> albumIds) {
        boolean result = true;
        for (Long albumId : albumIds) {
            result &= delete(albumId);
        }
        return result;
    }

    @Override
    public Album queryById(Long albumId) {
        return this.albumGateway.findById(albumId);
    }

    @Override
    public Page<AlbumCO> queryChildren(AlbumPageQry qry, PageQuery page) {
        Page<AlbumCO> pages = this.albumMapper.selectAlbumPages(page.build(), qry);
        // 检查文件是否存在
        this.checkAndHandleMissingFile(pages.getRecords());
        return pages;
    }

    /**
     * 检查并处理丢失的文件
     * 当系统管理员删除了OSS文件但相册记录仍存在时，需要清理相册记录
     *
     * @param albumCOList 相册记录
     */
    private void checkAndHandleMissingFile(List<AlbumCO> albumCOList) {

        if (albumCOList == null || albumCOList.isEmpty()) return;

        for (AlbumCO albumCO : albumCOList) {
            // 只检查文件类型的记录
            if (Album.TYPE_FILE.equals(albumCO.getType())) {
                // 检查系统OSS记录是否存在
                SysOssVo ossVo = ossService.getById(albumCO.getOssId());
                // 如果系统OSS记录不存在，说明文件已被管理员删除；则将文件标记为已删除
                if (ossVo == null) albumCO.setDeleted(true);
            }
        }

    }
}
