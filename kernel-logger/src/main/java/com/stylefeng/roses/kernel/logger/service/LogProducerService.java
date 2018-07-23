package com.stylefeng.roses.kernel.logger.service;

import com.stylefeng.roses.kernel.logger.entity.LoggerContent;
import com.stylefeng.roses.kernel.logger.entity.TimeConsumingLog;
import com.stylefeng.roses.kernel.logger.entity.TraceLog;

/**
 * 发送日志到消息队列的接口类
 *
 * @author yaoliguo
 * @date 2018-04-25 10:37
 */
public interface LogProducerService {

    /**
     * 发送日志
     *
     * @author yaoliguo
     * @date 2018-04-25 10:37
     */
    void sendMsg(LoggerContent log);

    /**
     * 发送trace日志
     *
     * @author fengshuonan
     * @Date 2018/5/15 下午7:16
     */
    void sendTraceMsg(TraceLog traceLog);

    /**
     * 发送接口调用时间日志
     *
     * @author fengshuonan
     * @Date 2018/5/15 下午7:16
     */
    void sendTcMsg(TimeConsumingLog timeConsumingLog);

}
