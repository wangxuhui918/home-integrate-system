

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.snowflake.utils;

import cn.bigcore.micro.snowflake.bean.FyyId;
import cn.bigcore.micro.snowflake.bean.FyyIdMeta;

import java.util.Date;

/**
 * 主键生成器,通过区分ID机器号实现分布式主键重复[cn.bigcore.micro.snowflake.utils.IdUtils.workerId]
 *
 * @author 汪旭辉
 * @date 2021年12月6日
 * @readme
 */
public class FyyIdUtils {
    // 上一毫秒数
    private static long lastTimestamp = -1L;
    // 毫秒内Sequence(0~4095)
    private static long sequence = 0L;
    // 机器ID(0-1023)
    public static long workerId = 1L;
    // 各种元数据
    protected FyyIdMeta idMeta;

//    public static void main(String[] args) {
//        IdUtils.workerId = 3;
//        System.out.println(IdUtils.genId());
//    }
//    /**
//     * 构造
//     *
//     * @param workerId 机器ID((0-1023)
//     */
//    public IdServiceUtils(long workerId) {
//        if (workerId > IdMeta.MAX_ID || workerId < 0) {
//            throw new IllegalArgumentException(
//                    String.format("worker Id can't be greater than %d or less than 0", IdMeta.MAX_ID));
//        }
//        this.workerId = workerId;
//        log.info("worker starting. timestamp left shift {},  worker id bits {}, sequence bits {}, workerid {}",
//                IdMeta.TIMESTAMP_LEFT_SHIFT_BITS, IdMeta.ID_BITS, IdMeta.SEQUENCE_BITS, workerId);
//    }

    /**
     * 生成ID（线程安全）
     *
     * @return id
     */
    public static String genIdString() {
        return genId() + "";
    }

    /**
     * 生成ID（线程安全）
     *
     * @return id
     */
    public static synchronized long genId() {
        long timestamp = timeGen();

        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟被修改过，回退在上一次ID生成时间之前应当抛出异常！！！
        validateTimestamp(timestamp, lastTimestamp);

        // 如果是同一时间生成的，则进行毫秒内sequence生成
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & FyyIdMeta.SEQUENCE_MASK;
            // 溢出处理
            if (sequence == 0) { // 阻塞到下一毫秒,获得新时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else { // 时间戳改变，毫秒内sequence重置
            sequence = 0L;
        }
        // 上次生成ID时间截
        lastTimestamp = timestamp;

        // 移位并通过或运算组成64位ID
        return ((timestamp - FyyIdMeta.START_TIME) << FyyIdMeta.TIMESTAMP_LEFT_SHIFT_BITS)
                | (workerId << FyyIdMeta.ID_SHIFT_BITS) | sequence;
    }

    /**
     * 如果当前时间戳小于上一次ID生成的时间戳，说明系统时钟被修改过，回退在上一次ID生成时间之前应当抛出异常！！！
     *
     * @param lastTimestamp 上一次ID生成的时间戳
     * @param timestamp     当前时间戳
     */
    private static void validateTimestamp(long timestamp, long lastTimestamp) {
        if (timestamp < lastTimestamp) {
//            log.error(String.format("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp));
            throw new IllegalStateException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
    }

    /**
     * 阻塞到下一毫秒,获得新时间戳
     *
     * @param lastTimestamp 上次生成ID时间截
     * @return 当前时间戳
     */
    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 对id进行解析
     *
     * @param id 生成的ID
     * @return 封装的ID类
     */
    public static FyyId expId(long id) {
        return FyyIdConverterUtils.convert(id);
    }

    /**
     * 对时间戳单独进行解析
     *
     * @param time 时间戳
     * @return 生成的Date时间
     */
    public static Date transTime(long time) {
        return new Date(time + FyyIdMeta.START_TIME);
    }

    /**
     * 根据时间戳和序列号生成ID
     *
     * @param timeStamp 时间戳
     * @param sequence  序列号
     * @return 生成的ID
     */
    public static long makeId(long timeStamp, long sequence) {
        return makeId(timeStamp, workerId, sequence);
    }

    /**
     * 根据时间戳、机器ID和序列号生成ID
     *
     * @param timeStamp 时间戳
     * @param worker    机器ID
     * @param sequence  序列号
     * @return 生成的ID
     */
    public static long makeId(long timeStamp, long worker, long sequence) {
        return FyyIdConverterUtils.convert(new FyyId(timeStamp, worker, sequence));
    }

    public static long getLastTimestamp() {
        return lastTimestamp;
    }

    public static void setLastTimestamp(long lastTimestamp) {
        FyyIdUtils.lastTimestamp = lastTimestamp;
    }

    public static long getSequence() {
        return sequence;
    }

    public static void setSequence(long sequence) {
        FyyIdUtils.sequence = sequence;
    }

    public static long getWorkerId() {
        return workerId;
    }

    public static void setWorkerId(long workerId) {
        FyyIdUtils.workerId = workerId;
    }

    public FyyIdMeta getIdMeta() {
        return idMeta;
    }

    public void setIdMeta(FyyIdMeta idMeta) {
        this.idMeta = idMeta;
    }
}
