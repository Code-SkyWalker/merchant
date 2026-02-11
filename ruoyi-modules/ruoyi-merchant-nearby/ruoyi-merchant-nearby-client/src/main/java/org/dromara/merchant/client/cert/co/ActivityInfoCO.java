package org.dromara.merchant.client.cert.co;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 活动信息CO
 *
 * @author Code Skywalker
 */
@Data
public class ActivityInfoCO {

    /**
     * 活动ID
     */
    private Long id;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动类型
     */
    private String type;

    /**
     * 活动开始时间
     */
    private LocalDateTime receiveBegin;

    /**
     * 活动结束时间
     */
    private LocalDateTime receiveEnd;

}
