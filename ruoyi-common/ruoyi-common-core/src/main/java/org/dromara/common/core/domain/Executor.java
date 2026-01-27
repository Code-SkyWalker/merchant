package org.dromara.common.core.domain;

/**
 * @Description 定义命令接口
 * @Author Code Skywalker
 * @Date 2025-10-23 13:30
 */
public interface Executor<T, U> {

    /**
     * 执行命令
     */
    U execute(T command);
}
