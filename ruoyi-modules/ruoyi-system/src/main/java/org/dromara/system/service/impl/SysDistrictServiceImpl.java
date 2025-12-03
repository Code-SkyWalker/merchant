package org.dromara.system.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.system.domain.SysDistrict;
import org.dromara.system.domain.bo.SysDistrictBo;
import org.dromara.system.domain.vo.SysDistrictVo;
import org.dromara.system.mapper.SysDistrictMapper;
import org.dromara.system.service.ISysDistrictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Description 行政区划Service业务层处理
 * @Author Code Skywalker
 * @Date 2025/12/3 09:37
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysDistrictServiceImpl implements ISysDistrictService {

    private final SysDistrictMapper baseMapper;

    /**
     * 查询行政区划
     */
    @Override
    public SysDistrictVo queryById(Long districtId) {
        return baseMapper.selectVoById(districtId);
    }

    /**
     * 查询行政区划列表
     */
    @Override
    public TableDataInfo<SysDistrictVo> queryPageList(SysDistrictBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysDistrict> lqw = buildQueryWrapper(bo);
        Page<SysDistrictVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询行政区划列表
     */
    @Override
    public List<SysDistrictVo> queryList(SysDistrictBo bo) {
        LambdaQueryWrapper<SysDistrict> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 根据父级ID查询子行政区划
     */
    @Override
    public List<SysDistrictVo> selectDistrictListByParentId(Long parentId) {
        LambdaQueryWrapper<SysDistrict> lqw = Wrappers.lambdaQuery();
        lqw.eq(SysDistrict::getParentId, parentId);
        lqw.orderByAsc(SysDistrict::getOrderNum);
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysDistrict entity) {
        // TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验行政区划编码是否唯一
     */
    @Override
    public boolean checkAdcodeUnique(SysDistrictBo bo) {
        boolean flag = false;
        SysDistrict sysDistrict = baseMapper.selectOne(new LambdaQueryWrapper<SysDistrict>()
            .eq(SysDistrict::getAdcode, bo.getAdcode()).last("limit 1"));
        if (Objects.nonNull(sysDistrict) && sysDistrict.getDistrictId() != bo.getDistrictId()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 构建查询对象
     */
    private LambdaQueryWrapper<SysDistrict> buildQueryWrapper(SysDistrictBo bo) {
        LambdaQueryWrapper<SysDistrict> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getDistrictName()), SysDistrict::getDistrictName, bo.getDistrictName());
        lqw.eq(StringUtils.isNotBlank(bo.getAdcode()), SysDistrict::getAdcode, bo.getAdcode());
        lqw.eq(StringUtils.isNotBlank(bo.getLevel()), SysDistrict::getLevel, bo.getLevel());
        lqw.eq(bo.getParentId() != null, SysDistrict::getParentId, bo.getParentId());
        return lqw;
    }

    /**
     * 批量删除行政区划
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 获取高德地图API数据并同步到数据库
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncDistrictDataFromGaode(String keywords, Integer subdistrict) {
        // 高德地图API配置
        String gaodeApiKey = "5f195adb4d49aa86b3aeba4a58946517";
        String url = "https://restapi.amap.com/v3/config/district";

        // 构造请求参数
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("key", gaodeApiKey);
        paramMap.put("keywords", keywords);
        paramMap.put("subdistrict", subdistrict);
        paramMap.put("page", 1);
        paramMap.put("offset", 200);
        paramMap.put("extensions", "base"); // 不返回边界坐标，减少数据量

        try {
            // 发送HTTP请求
            String result = HttpUtil.get(url, paramMap);

            // 解析JSON数据
            if (JSONUtil.isTypeJSON(result)) {
                JSONObject jsonObject = JSONUtil.parseObj(result);
                if ("1".equals(jsonObject.getStr("status"))) {
                    // 解析行政区划数据
                    JSONArray districtsArray = jsonObject.getJSONArray("districts");
                    if (districtsArray != null && !districtsArray.isEmpty()) {
                        parseAndSaveDistrictData(districtsArray, 0L, "0"); // 0表示根节点
                    }
                } else {
                    log.error("高德地图API调用失败，错误信息：{}", jsonObject.getStr("info"));
                }
            }
        } catch (Exception e) {
            log.error("同步高德地图行政区划数据异常", e);
        }
    }

    /**
     * 解析并保存行政区划数据
     *
     * @param districtsArray 行政区划数据数组
     * @param parentId       父级ID
     * @param ancestors      祖级列表
     */
    private void parseAndSaveDistrictData(JSONArray districtsArray, Long parentId, String ancestors) {
        for (int i = 0; i < districtsArray.size(); i++) {
            JSONObject districtObj = districtsArray.getJSONObject(i);

            // 创建行政区划对象
            SysDistrict district = new SysDistrict();
            district.setDistrictId(Long.valueOf(districtObj.getStr("adcode")));
            district.setParentId(parentId);
            district.setAncestors(ancestors);
            district.setDistrictName(districtObj.getStr("name"));
            district.setAdcode(districtObj.getStr("adcode"));
            district.setLevel(districtObj.getStr("level"));
            district.setCenter(districtObj.getStr("center"));
            district.setOrderNum(i);

            // 检查是否已存在相同adcode的记录
            LambdaQueryWrapper<SysDistrict> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(SysDistrict::getAdcode, district.getAdcode());
            SysDistrict existingDistrict = baseMapper.selectOne(queryWrapper);

            Long districtId;
            if (existingDistrict != null) {
                // 更新已存在的记录
                district.setDistrictId(existingDistrict.getDistrictId());
                baseMapper.updateById(district);
                districtId = existingDistrict.getDistrictId();
            } else {
                // 插入新记录
                baseMapper.insert(district);
                districtId = district.getDistrictId();
            }

            // 递归处理子级行政区划
            JSONArray subDistricts = districtObj.getJSONArray("districts");
            if (subDistricts != null && !subDistricts.isEmpty()) {
                String newAncestors = ancestors.equals("0") ? String.valueOf(districtId) : ancestors + "," + districtId;
                parseAndSaveDistrictData(subDistricts, districtId, newAncestors);
            }
        }
    }
}
