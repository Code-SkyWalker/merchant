package org.dromara.merchant.adapter.web.marketing;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.merchant.app.marketing.IMarketingService;
import org.dromara.merchant.client.marketing.dto.data.client.MarketingCO;
import org.dromara.merchant.client.marketing.dto.data.client.MarketingPageCo;
import org.dromara.merchant.client.marketing.dto.data.command.MarketingCreateCmd;
import org.dromara.merchant.client.marketing.dto.data.command.MarketingModifyCmd;
import org.dromara.merchant.client.marketing.dto.data.command.query.MarketingPageQry;
import org.dromara.merchant.infrastructure.marketing.converter.MarketingConvertor;
import org.dromara.merchant.infrastructure.marketing.mapper.MarketingMapper;
import org.springframework.web.bind.annotation.*;

/**
 * 营销活动管理
 *
 * @Author Code Skywalker
 * @Date 2025/12/26 15:19
 */
@RestController
@RequestMapping("/merchant/marketing")
@RequiredArgsConstructor
public class MarketingController {

    private final IMarketingService marketingService;

    private final MarketingMapper marketingMapper;

    private final MarketingConvertor marketingConvertor;

    /**
     * 创建营销活动
     *
     * @param cmd 营销活动创建命令
     * @return 创建结果
     */
    @PostMapping
    public R<Boolean> create(@RequestBody MarketingCreateCmd cmd) {
        boolean created = marketingService.create(cmd);
        return created ? R.ok(true) : R.fail("创建失败");
    }

    /**
     * 修改营销活动
     *
     * @param cmd 营销活动修改命令
     * @return 修改结果
     */
    @PutMapping
    public R<Boolean> modify(@RequestBody MarketingModifyCmd cmd) {
        boolean modified = marketingService.modify(cmd);
        return modified ? R.ok(true) : R.fail("修改失败");
    }

    /**
     * 删除营销活动
     *
     * @param id 营销活动Id
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        boolean deleted = marketingService.delete(id);
        return deleted ? R.ok(true) : R.fail("删除失败");
    }

    /**
     * 根据Id查询营销活动
     *
     * @param id 营销活动Id
     * @return 营销活动
     */
    @GetMapping("/{id}")
    public R<MarketingCO> queryById(@PathVariable Long id) {
        MarketingCO marketing = marketingMapper.queryById(id);
        return marketing != null ? R.ok(marketing) : R.fail("查询失败");
    }

    /**
     * 列表查询营销活动
     *
     * @return 营销活动列表
     */
    @GetMapping("/page")
    public TableDataInfo<MarketingPageCo> queryPages(@ModelAttribute MarketingPageQry qry, @ModelAttribute PageQuery page) {
        Page<MarketingPageCo> marketing = marketingMapper.queryPages(qry, page.build());
        return TableDataInfo.build(marketing);
    }

}
