package org.dromara.merchant.infrastructure.merchant.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.client.merchant.dto.data.clientobject.AlbumCO;
import org.dromara.merchant.client.merchant.dto.data.command.query.AlbumPageQry;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.AlbumDO;

/**
 * @Description 相册Mapper接口
 * @Author Code Skywalker
 * @Date 2025/12/17 15:45
 */
public interface AlbumMapper extends BaseMapperPlus<AlbumDO, AlbumDO> {

    /**
     * 查询指定文件夹下的所有子项
     *
     * @param page 分页参数
     * @param qry  查询条件
     * @return 子项列表
     */
    Page<AlbumCO> selectAlbumPages(Page<AlbumCO> page, @Param("qry") AlbumPageQry qry);
}
