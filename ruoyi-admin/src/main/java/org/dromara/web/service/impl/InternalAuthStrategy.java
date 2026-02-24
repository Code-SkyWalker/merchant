package org.dromara.web.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.constant.Constants;
import org.dromara.common.core.constant.SystemConstants;
import org.dromara.common.core.constant.TenantConstants;
import org.dromara.common.core.domain.model.InternalLoginBody;
import org.dromara.common.core.domain.model.LoginUser;
import org.dromara.common.core.enums.LoginType;
import org.dromara.common.core.exception.user.UserException;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.core.utils.ValidatorUtils;
import org.dromara.common.json.utils.JsonUtils;
import org.dromara.common.tenant.exception.TenantException;
import org.dromara.common.tenant.helper.TenantHelper;
import org.dromara.system.domain.SysUser;
import org.dromara.system.domain.bo.SysTenantBo;
import org.dromara.system.domain.bo.SysUserBo;
import org.dromara.system.domain.vo.SysClientVo;
import org.dromara.system.domain.vo.SysTenantVo;
import org.dromara.system.domain.vo.SysUserVo;
import org.dromara.system.mapper.SysUserMapper;
import org.dromara.system.service.ISysTenantService;
import org.dromara.system.service.ISysUserService;
import org.dromara.system.domain.SysDept;
import org.dromara.system.domain.SysRole;
import org.dromara.system.domain.SysUserRole;
import org.dromara.system.mapper.SysDeptMapper;
import org.dromara.system.mapper.SysRoleMapper;
import org.dromara.system.mapper.SysUserRoleMapper;
import org.dromara.web.domain.vo.LoginVo;
import org.dromara.web.service.IAuthStrategy;
import org.dromara.web.service.SysLoginService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description 内部应用认证策略
 * @Author Code Skywalker
 * @Date 2025/12/19 13:28
 */
@Slf4j
@Service("internal" + IAuthStrategy.BASE_NAME)
@RequiredArgsConstructor
public class InternalAuthStrategy implements IAuthStrategy {

    private final SysUserMapper userMapper;
    private final ISysTenantService tenantService;
    private final ISysUserService userService;
    private final SysLoginService loginService;
    private final SysDeptMapper deptMapper;
    private final SysRoleMapper roleMapper;
    private final SysUserRoleMapper userRoleMapper;

    @Override
    public LoginVo login(String body, SysClientVo client) {
        InternalLoginBody loginBody = JsonUtils.parseObject(body, InternalLoginBody.class);
        ValidatorUtils.validate(loginBody);
        String tenantId = loginBody.getTenantId();

        // 校验租户
        boolean tenantExists = checkTenant(tenantId);

        String username = loginBody.getUsername();
        Integer loginUserType = loginBody.getLoginUserType();

        // 如果租户不存在，则创建租户和管理员用户
        if (!tenantExists) {
            createTenant(loginBody, loginUserType);
            // 使用创建的管理员用户登录
            return login(client, tenantId, username);
        }

        // 租户存在，检查用户是否存在
        SysUserVo user = TenantHelper.dynamic(tenantId, () -> userMapper.selectVoOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName, username)));

        // 如果用户不存在，则创建用户
        if (ObjectUtil.isNull(user)) {
            createUser(loginBody, tenantId);
            // 使用创建的用户登录
            return login(client, tenantId, username);
        }

        return login(client, tenantId, username);
    }

    /**
     * 使用管理员身份登录
     *
     * @param client   客户端信息
     * @param tenantId 租户ID
     * @param username 用户名
     * @return 登录结果
     */
    private LoginVo login(SysClientVo client, String tenantId, String username) {
        LoginUser loginUser = TenantHelper.dynamic(tenantId, () -> {
            SysUserVo user = loadUserByUsername(username);
            loginService.checkLogin(
                LoginType.PASSWORD, tenantId, username,
                () -> false
            );
            // 此处可根据登录用户的数据不同 自行创建 loginUser
            return loginService.buildLoginUser(user);
        });

        // 设置登录信息
        return IAuthStrategy.generateLoginVO(client, loginUser);
    }

    /**
     * 创建租户和管理员用户
     *
     * @param loginBody 登录信息
     */
    private void createTenant(InternalLoginBody loginBody, Integer loginUserType) {
        // 创建租户
        SysTenantBo tenantBo = new SysTenantBo();
        tenantBo.setTenantId(loginBody.getTenantId());
        tenantBo.setContactUserName(loginBody.getContactUserName());
        tenantBo.setContactPhone(loginBody.getContactPhone());
        tenantBo.setCompanyName(loginBody.getCompanyName());
        tenantBo.setUsername(loginBody.getUsername());
        tenantBo.setPassword(loginBody.getPassword());

        // 根据登录用户类型设置租户套餐
        tenantBo.setPackageId(getPackageId(loginBody));

        tenantService.insertByBo(tenantBo);
    }

    /**
     * 创建普通用户
     *
     * @param loginBody 登录信息
     * @param tenantId  租户ID
     */
    private void createUser(InternalLoginBody loginBody, String tenantId) {
        long packageId = getPackageId(loginBody);
        createUserWithPackagePermissions(tenantId, loginBody.getUsername(), loginBody.getPassword(),
            packageId, loginBody.getCompanyName());
    }

    private static long getPackageId(InternalLoginBody loginBody) {
        long packageId;
        if (loginBody.getLoginUserType() == 1) packageId = 2001925927655182338L;        // 租户管理员
        else if (loginBody.getLoginUserType() == 2) packageId = 2017113387925131265L;   // 商户管理员
        else packageId = 2017146296534978561L;                           // 普通用户
        return packageId;
    }

    /**
     * 根据租户套餐创建具有相应权限的用户
     *
     * @param tenantId   租户ID
     * @param username   用户名
     * @param password   密码
     * @param packageId  套餐ID
     * @param companyName 公司名称
     */
    private void createUserWithPackagePermissions(String tenantId, String username, String password, Long packageId, String companyName) {
        TenantHelper.dynamic(tenantId, () -> {
            // 创建部门
            SysDept dept = new SysDept();
            dept.setTenantId(tenantId);
            dept.setDeptName(companyName);
            dept.setParentId(Constants.TOP_PARENT_ID);
            dept.setAncestors(Constants.TOP_PARENT_ID.toString());
            deptMapper.insert(dept);
            Long deptId = dept.getDeptId();

            // 根据套餐创建角色和权限
            Long roleId = tenantService.createTenantRole(tenantId, packageId);

            // 创建用户
            SysUser user = new SysUser();
            user.setTenantId(tenantId);
            user.setUserName(username);
            user.setNickName(username);
            user.setPassword(BCrypt.hashpw(password));
            user.setDeptId(deptId);
            userMapper.insert(user);
            Long userId = user.getUserId();

            // 设置用户为部门负责人
            SysDept updateDept = new SysDept();
            updateDept.setLeader(userId);
            updateDept.setDeptId(deptId);
            deptMapper.updateById(updateDept);

            // 用户和角色关联
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleMapper.insert(userRole);

            return null;
        });
    }

    /**
     * 校验租户
     *
     * @param tenantId   租户ID
     * @return 租户是否存在
     */
    public boolean checkTenant(String tenantId) {
        if (!TenantHelper.isEnable()) return true;
        if (StringUtils.isBlank(tenantId)) {
            throw new TenantException("tenant.number.not.blank");
        }
        if (TenantConstants.DEFAULT_TENANT_ID.equals(tenantId)) return true;
        SysTenantVo tenant = tenantService.queryByTenantId(tenantId);
        if (ObjectUtil.isNull(tenant)) {
            return false;
        } else if (SystemConstants.DISABLE.equals(tenant.getStatus())) {
            log.info("登录租户：{} 已被停用.", tenantId);
            throw new TenantException("tenant.blocked");
        } else if (ObjectUtil.isNotNull(tenant.getExpireTime())
            && new Date().after(tenant.getExpireTime())) {
            log.info("登录租户：{} 已超过有效期.", tenantId);
            throw new TenantException("tenant.expired");
        }
        return true;
    }

    private SysUserVo loadUserByUsername(String username) {
        SysUserVo user = userMapper.selectVoOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName, username));
        if (ObjectUtil.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new UserException("user.not.exists", username);
        } else if (SystemConstants.DISABLE.equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new UserException("user.blocked", username);
        }
        return user;
    }

}
