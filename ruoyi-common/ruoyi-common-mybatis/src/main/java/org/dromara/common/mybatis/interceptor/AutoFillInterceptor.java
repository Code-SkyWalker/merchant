package org.dromara.common.mybatis.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.satoken.utils.LoginHelper;

import java.time.LocalDateTime;
import java.util.Properties;

/**
 * 自动填充拦截器
 * 在不继承BaseMapper的情况下实现MyBatis自动填充功能
 *
 * @author your-name
 */
@Slf4j
@Intercepts({
    @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class AutoFillInterceptor implements Interceptor {

    /**
     * 如果用户不存在默认注入-1代表无用户
     */
    private static final Long DEFAULT_USER_ID = -1L;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取方法参数
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        Object parameter = args[1];

        // 获取SQL命令类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        // 只处理INSERT和UPDATE操作
        if (sqlCommandType == SqlCommandType.INSERT || sqlCommandType == SqlCommandType.UPDATE) {
            // 处理参数对象
            handleParameter(parameter, sqlCommandType);
        }

        // 执行原方法
        return invocation.proceed();
    }

    /**
     * 处理参数对象，进行自动填充
     *
     * @param parameter 参数对象
     * @param sqlCommandType SQL命令类型
     */
    private void handleParameter(Object parameter, SqlCommandType sqlCommandType) {
        if (parameter == null) {
            return;
        }

        try {
            // 如果参数对象是BaseEntity的实例
            if (parameter instanceof BaseEntity baseEntity) {
                // 创建MetaObject

                MetaObject metaObject = SystemMetaObject.forObject(parameter);

                // 根据SQL命令类型进行不同的填充处理
                if (sqlCommandType == SqlCommandType.INSERT) {
                    handleInsertFill(baseEntity, metaObject);
                } else if (sqlCommandType == SqlCommandType.UPDATE) {
                    handleUpdateFill(baseEntity, metaObject);
                }
            }
        } catch (Exception e) {
            log.warn("自动填充失败: {}", e.getMessage());
        }
    }

    /**
     * 处理插入时的字段填充
     *
     * @param baseEntity 实体对象
     * @param metaObject 元对象
     */
    private void handleInsertFill(BaseEntity baseEntity, MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();

        // 填充创建时间（如果为空）
        if (baseEntity.getCreateTime() == null) {
            baseEntity.setCreateTime(now);
            setValueIfPresent(metaObject, "createTime", now);
        }

        // 填充更新时间
        baseEntity.setUpdateTime(now);
        setValueIfPresent(metaObject, "updateTime", now);

        // 获取当前登录用户信息
        Long userId = getCurrentUserId();

        // 填充创建人（如果为空）
        if (baseEntity.getCreateBy() == null) {
            baseEntity.setCreateBy(userId);
            setValueIfPresent(metaObject, "createBy", userId);
        }

        // 填充更新人
        baseEntity.setUpdateBy(userId);
        setValueIfPresent(metaObject, "updateBy", userId);

        // 填充创建部门（如果为空）
        if (baseEntity.getCreateDept() == null) {
            Long deptId = getCurrentDeptId();
            if (deptId != null) {
                baseEntity.setCreateDept(deptId);
                setValueIfPresent(metaObject, "createDept", deptId);
            }
        }
    }

    /**
     * 处理更新时的字段填充
     *
     * @param baseEntity 实体对象
     * @param metaObject 元对象
     */
    private void handleUpdateFill(BaseEntity baseEntity, MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();

        // 填充更新时间
        baseEntity.setUpdateTime(now);
        setValueIfPresent(metaObject, "updateTime", now);

        // 填充更新人
        Long userId = getCurrentUserId();
        baseEntity.setUpdateBy(userId);
        setValueIfPresent(metaObject, "updateBy", userId);
    }

    /**
     * 获取当前登录用户ID
     *
     * @return 用户ID
     */
    private Long getCurrentUserId() {
        try {
            return LoginHelper.getUserId();
        } catch (Exception e) {
            return DEFAULT_USER_ID;
        }
    }

    /**
     * 获取当前登录用户部门ID
     *
     * @return 部门ID
     */
    private Long getCurrentDeptId() {
        try {
            return LoginHelper.getDeptId();
        } catch (Exception e) {
            return DEFAULT_USER_ID;
        }
    }

    /**
     * 如果字段存在则设置值
     *
     * @param metaObject 元对象
     * @param fieldName 字段名
     * @param value 值
     */
    private void setValueIfPresent(MetaObject metaObject, String fieldName, Object value) {
        try {
            // 检查字段是否存在且有对应的setter方法
            if (metaObject.hasSetter(fieldName) && value != null) {
                metaObject.setValue(fieldName, value);
            }
        } catch (Exception e) {
            // 忽略设置失败的情况
            log.debug("设置字段{}失败: {}", fieldName, e.getMessage());
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 可以设置一些自定义属性
    }
}
