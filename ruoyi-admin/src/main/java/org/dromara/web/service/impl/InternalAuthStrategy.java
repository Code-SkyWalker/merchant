package org.dromara.web.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.stp.parameter.SaLoginParameter;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.constant.SystemConstants;
import org.dromara.common.core.constant.TenantConstants;
import org.dromara.common.core.domain.model.InternalLoginBody;
import org.dromara.common.core.domain.model.LoginUser;
import org.dromara.common.core.enums.LoginType;
import org.dromara.common.core.enums.UserType;
import org.dromara.common.core.exception.user.UserException;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.core.utils.ValidatorUtils;
import org.dromara.common.json.utils.JsonUtils;
import org.dromara.common.satoken.utils.LoginHelper;
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
import org.dromara.web.domain.vo.LoginVo;
import org.dromara.web.service.IAuthStrategy;
import org.dromara.web.service.SysLoginService;
import org.jetbrains.annotations.NotNull;
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

    @Override
    public LoginVo login(String body, SysClientVo client) {
        InternalLoginBody loginBody = JsonUtils.parseObject(body, InternalLoginBody.class);
        ValidatorUtils.validate(loginBody);
        String tenantId = loginBody.getTenantId();

        // 校验租户
        boolean tenantExists = checkTenant(tenantId, loginBody);

        String username = loginBody.getUsername();
        String password = loginBody.getPassword();

        // 如果租户不存在，则创建租户和管理员用户
        if (!tenantExists) {
            createTenantAndUser(loginBody);
            // 使用创建的管理员用户登录
            return loginAsAdmin(client, tenantId, username, password);
        }

        // 租户存在，检查用户是否存在
        SysUserVo user = TenantHelper.dynamic(tenantId, () -> {
            return userMapper.selectVoOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName, username));
        });

        // 如果用户不存在，则创建普通用户
        if (ObjectUtil.isNull(user)) {
            createUser(loginBody, tenantId);
            // 使用创建的普通用户登录
            return loginAsUser(client, tenantId, username, password);
        }

        // 用户存在，正常登录流程
        LoginUser loginUser = TenantHelper.dynamic(tenantId, () -> {
            loginService.checkLogin(
                LoginType.PASSWORD, tenantId, username,
                () -> !BCrypt.checkpw(password, user.getPassword())
            );
            // 此处可根据登录用户的数据不同 自行创建 loginUser
            return loginService.buildLoginUser(user);
        });

        return IAuthStrategy.generateLoginVO(client, loginUser);
    }

    /**
     * 使用管理员身份登录
     *
     * @param client   客户端信息
     * @param tenantId 租户ID
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    private LoginVo loginAsAdmin(SysClientVo client, String tenantId, String username, String password) {
        LoginUser loginUser = TenantHelper.dynamic(tenantId, () -> {
            SysUserVo user = loadUserByUsername(username);
            loginService.checkLogin(
                LoginType.PASSWORD, tenantId, username,
                () -> !BCrypt.checkpw(password, user.getPassword())
            );
            // 此处可根据登录用户的数据不同 自行创建 loginUser
            return loginService.buildLoginUser(user);
        });

        // 设置登录信息
        LoginVo loginVo = IAuthStrategy.generateLoginVO(client, loginUser);

        return loginVo;
    }

    /**
     * 使用普通用户身份登录
     *
     * @param client   客户端信息
     * @param tenantId 租户ID
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    private LoginVo loginAsUser(SysClientVo client, String tenantId, String username, String password) {
        LoginUser loginUser = TenantHelper.dynamic(tenantId, () -> {
            SysUserVo user = loadUserByUsername(username);
            loginService.checkLogin(
                LoginType.PASSWORD, tenantId, username,
                () -> !BCrypt.checkpw(password, user.getPassword())
            );
            // 此处可根据登录用户的数据不同 自行创建 loginUser
            return loginService.buildLoginUser(user);
        });

        // 设置登录信息
        LoginVo loginVo = IAuthStrategy.generateLoginVO(client, loginUser);

        return loginVo;
    }

    /**
     * 创建租户和管理员用户
     *
     * @param loginBody 登录信息
     */
    private void createTenantAndUser(InternalLoginBody loginBody) {
        // 创建租户
        SysTenantBo tenantBo = new SysTenantBo();
        tenantBo.setTenantId(loginBody.getTenantId());
        tenantBo.setContactUserName(loginBody.getContactUserName());
        tenantBo.setContactPhone(loginBody.getContactPhone());
        tenantBo.setCompanyName(loginBody.getCompanyName());
        tenantBo.setUsername(loginBody.getUsername());
        tenantBo.setPassword(loginBody.getPassword());
        tenantBo.setPackageId(2001925927655182338L);
        tenantService.insertByBo(tenantBo);
    }

    /**
     * 创建普通用户
     *
     * @param loginBody 登录信息
     * @param tenantId  租户ID
     */
    private void createUser(InternalLoginBody loginBody, String tenantId) {
        SysUserBo userBo = new SysUserBo();
        userBo.setUserName(loginBody.getUsername());
        userBo.setNickName(loginBody.getUsername());
        userBo.setPassword(BCrypt.hashpw(loginBody.getPassword()));
        userBo.setUserType(loginBody.getUserType().getUserType());

        // 在对应的租户下创建用户
        TenantHelper.dynamic(tenantId, () -> {
            userService.registerUser(userBo, tenantId);
            return null;
        });
    }

    /**
     * 校验租户
     *
     * @param tenantId   租户ID
     * @param loginBody  登录信息
     * @return 租户是否存在
     */
    public boolean checkTenant(String tenantId, InternalLoginBody loginBody) {
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
