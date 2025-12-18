package org.dromara.merchant.adapter.web.merchant;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.merchant.app.merchant.IAlbumService;
import org.dromara.merchant.client.album.dto.data.clientobject.AlbumCO;
import org.dromara.merchant.client.album.dto.data.command.AlbumCreateCmd;
import org.dromara.merchant.client.album.dto.data.command.AlbumModifyCmd;
import org.dromara.merchant.client.album.dto.data.command.query.AlbumPageQry;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

/**
 * 相册管理接口
 * @Author Code Skywalker
 * @Date 2025/12/17 16:25
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/merchant/album")
public class AlbumController extends BaseController {

    private final IAlbumService albumService;

    /**
     * 创建文件夹
     */
    @SaCheckPermission("merchant:album:add")
    @Log(title = "相册管理", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Boolean> add(@Validated @RequestBody AlbumCreateCmd cmd) {
        return R.ok(albumService.create(cmd));
    }

    /**
     * 修改文件夹
     */
    @SaCheckPermission("merchant:album:edit")
    @Log(title = "相册管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Boolean> edit(@Validated @RequestBody AlbumModifyCmd cmd) {
        return R.ok(albumService.modify(cmd));
    }

    /**
     * 上传文件到文件夹
     */
    @SaCheckPermission("merchant:album:upload")
    @Log(title = "相册上传文件", businessType = BusinessType.INSERT)
    @PostMapping("/upload")
    public R<Boolean> upload(@RequestParam Long merchantId,
                             @RequestParam(required = false) Long folderId,
                             @RequestPart @NotNull(message = "上传文件不能为空") MultipartFile file) {
        return R.ok(albumService.uploadFile(merchantId, folderId, file));
    }

    /**
     * 删除文件或文件夹
     */
    @SaCheckPermission("merchant:album:remove")
    @Log(title = "相册管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{albumIds}")
    public R<Boolean> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] albumIds) {
        return R.ok(albumService.delete(Arrays.asList(albumIds)));
    }

    /**
     * 查询指定文件夹下的所有子项
     */
    @SaCheckPermission("merchant:album:query")
    @GetMapping("/children")
    public TableDataInfo<AlbumCO> children(@ModelAttribute AlbumPageQry qry, @ModelAttribute PageQuery pageQuery) {
        Page<AlbumCO> pages = albumService.queryChildren(qry, pageQuery);
        return TableDataInfo.build(pages);
    }
}
