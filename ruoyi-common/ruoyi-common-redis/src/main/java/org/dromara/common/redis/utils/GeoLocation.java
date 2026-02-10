package org.dromara.common.redis.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 地理位置信息类
 * @Author Code Skywalker
 * @Date 2026/2/9 16:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoLocation<T> {

    private double longitude;
    private double latitude;
    private T member;

}
