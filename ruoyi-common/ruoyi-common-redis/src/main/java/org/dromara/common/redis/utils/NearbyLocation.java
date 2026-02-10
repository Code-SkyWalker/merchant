package org.dromara.common.redis.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 附近地点信息类
 * @Author Code Skywalker
 * @Date 2026/2/9 16:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NearbyLocation<T> {
    private T location;
    private double distance;
}
