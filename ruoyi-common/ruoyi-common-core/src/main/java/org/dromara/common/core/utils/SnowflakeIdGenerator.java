package org.dromara.common.core.utils;

/**
 * 雪花算法工具类，用于生成分布式唯一 ID。
 */
public class SnowflakeIdGenerator {
    /** 起始时间戳，可自定义，这里以 2020-01-01 00:00:00 为例 */
    private final long startTimeStamp = 1577836800000L;

    /** 机器 ID 所占的位数 */
    private final long workerIdBits = 5L;
    /** 数据中心 ID 所占的位数 */
    private final long dataCenterIdBits = 5L;
    /** 序列号所占的位数 */
    private final long sequenceBits = 12L;

    /** 机器 ID 最大值 */
    private final long maxWorkerId = ~(-1L << workerIdBits);
    /** 数据中心 ID 最大值 */
    private final long maxDataCenterId = ~(-1L << dataCenterIdBits);

    /** 机器 ID 向左移位数 */
    private final long workerIdShift = sequenceBits;
    /** 数据中心 ID 向左移位数 */
    private final long dataCenterIdShift = sequenceBits + workerIdBits;
    /** 时间戳向左移位数 */
    private final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;

    /** 序列号掩码 */
    private final long sequenceMask = ~(-1L << sequenceBits);

    /** 工作机器 ID (0~31) */
    private final long workerId;
    /** 数据中心 ID (0~31) */
    private final long dataCenterId;
    /** 序列号 */
    private long sequence = 0L;
    /** 上次生成 ID 的时间戳 */
    private long lastTimestamp = -1L;

    /**
     * 构造函数，初始化工作机器 ID 和数据中心 ID。
     *
     * @param workerId     工作机器 ID (0~31)
     * @param dataCenterId 数据中心 ID (0~31)
     */
    public SnowflakeIdGenerator(long workerId, long dataCenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("Worker ID can't be greater than %d or less than 0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("Data center ID can't be greater than %d or less than 0", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 生成下一个唯一 ID。
     *
     * @return 唯一 ID
     */
    public synchronized long nextId() {
        long currentTimestamp = System.currentTimeMillis();

        // 时钟回拨处理
        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id for " + (lastTimestamp - currentTimestamp) + " milliseconds");
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                // 序列号用完，等待下一毫秒
                currentTimestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            // 时间戳改变，重置序列号
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        return ((currentTimestamp - startTimeStamp) << timestampLeftShift) |
                (dataCenterId << dataCenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    /**
     * 等待下一毫秒。
     *
     * @param lastTimestamp 上次生成 ID 的时间戳
     * @return 下一毫秒的时间戳
     */
    private long waitNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    /** 单例实例（可通过 Spring 注入或静态方式获取） */
    private static final SnowflakeIdGenerator INSTANCE = new SnowflakeIdGenerator(1, 1);

    public static Long generateId() {
        return INSTANCE.nextId(); // 使用单例实例生成 ID
    }
}
