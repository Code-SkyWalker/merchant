package org.dromara.merchant.app.marketing;

import lombok.NonNull;
import org.dromara.merchant.client.marketing.dto.data.command.MarketingCreateCmd;
import org.dromara.merchant.client.marketing.dto.data.command.MarketingModifyCmd;
import org.dromara.merchant.domain.marketing.model.activity.Marketing;

import java.util.List;

/**
 * @Description 营销活动服务接口
 * @Author Code Skywalker
 * @Date 2025/12/26 15:14
 */
public interface IMarketingService {

    /**
     * 创建营销活动
     *
     * @param cmd 创建命令
     * @return 是否成功
     */
    boolean create(MarketingCreateCmd cmd);

    /**
     * 修改营销活动
     *
     * @param cmd 修改命令
     * @return 是否成功
     */
    boolean modify(MarketingModifyCmd cmd);

    /**
     * 删除营销活动
     *
     * @param id 营销活动ID
     * @return 是否成功
     */
    boolean delete(Long id);

    /**
     * 查询营销活动
     *
     * @param id 营销活动ID
     * @return 营销活动
     */
    Marketing queryByMarketingId(Long id);

    /**
     * 根据营销活动ID列表查询营销活动
     *
     * @param marketingIds 营销活动ID列表
     * @return 营销活动列表
     */
    List<Marketing> queryByIds(@NonNull List<Long> marketingIds);
}
