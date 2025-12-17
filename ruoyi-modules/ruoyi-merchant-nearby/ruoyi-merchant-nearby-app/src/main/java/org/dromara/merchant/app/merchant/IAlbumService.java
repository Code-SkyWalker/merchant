package org.dromara.merchant.app.merchant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.album.dto.data.clientobject.AlbumCO;
import org.dromara.merchant.client.album.dto.data.command.AlbumCreateCmd;
import org.dromara.merchant.client.album.dto.data.command.AlbumModifyCmd;
import org.dromara.merchant.client.album.dto.data.command.query.AlbumPageQry;
import org.dromara.merchant.domain.merchant.model.Album;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description 相册服务接口
 * @Author Code Skywalker
 * @Date 2025/12/17 16:05
 */
public interface IAlbumService {

    /**
     * 创建相册/文件夹
     *
     * @param cmd 相册创建命令
     * @return 是否创建成功
     */
    boolean create(AlbumCreateCmd cmd);

    /**
     * 修改相册/文件夹
     *
     * @param cmd 相册修改命令
     * @return 是否修改成功
     */
    boolean modify(AlbumModifyCmd cmd);

    /**
     * 上传文件到相册
     *
     * @param merchantId 商家ID
     * @param folderId   文件夹ID
     * @param file       上传的文件
     * @return 是否上传成功
     */
    boolean uploadFile(Long merchantId, Long folderId, MultipartFile file);

    /**
     * 删除相册项（文件或文件夹）
     *
     * @param albumId 相册项ID
     * @return 是否删除成功
     */
    boolean delete(Long albumId);

    /**
     * 批量删除相册项
     *
     * @param albumIds 相册项ID列表
     * @return 是否删除成功
     */
    boolean delete(List<Long> albumIds);

    /**
     * 根据ID查询相册项详情
     *
     * @param albumId 相册项ID
     * @return 相册项详情
     */
    Album queryById(Long albumId);

    /**
     * 查询指定文件夹下的所有子项
     *
     * @param qry  查询条件
     * @param page 分页参数
     * @return 子项列表
     */
    Page<AlbumCO> queryChildren(AlbumPageQry qry, PageQuery page);
}
