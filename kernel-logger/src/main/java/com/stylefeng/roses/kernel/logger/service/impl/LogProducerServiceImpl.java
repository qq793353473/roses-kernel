package com.stylefeng.roses.kernel.logger.service.impl;

import com.stylefeng.roses.kernel.logger.constants.KafkaConstants;
import com.stylefeng.roses.kernel.logger.entity.LoggerContent;
import com.stylefeng.roses.kernel.logger.entity.TimeConsumingLog;
import com.stylefeng.roses.kernel.logger.entity.TraceLog;
import com.stylefeng.roses.kernel.logger.service.LogProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 发送日志到消息队列的实现类
 *
 * @author yaoliguo
 * @date 2018-04-25 10:37
 */
public class LogProducerServiceImpl implements LogProducerService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Autowired
    private KafkaTemplate<String, Object> template;

    @Override
    public void sendMsg(LoggerContent log) {

        executorService.execute(() -> {
            try {
                template.send(KafkaConstants.LOG_TOPIC, KafkaConstants.LOG_TOPIC_KEY, log);
            } catch (Exception e) {
                logger.error("记录普通日志到kafka错误!", e);
            }
        });

    }

    @Override
    public void sendTraceMsg(TraceLog traceLog) {

        executorService.execute(() -> {
            try {
                template.send(KafkaConstants.TRACE_LOG_TOPIC, KafkaConstants.TRACE_LOG_TOPIC_KEY, traceLog);
            } catch (Exception e) {
                logger.error("记录trace日志到kafka错误!", e);
            }
        });

    }

    @Override
    public void sendTcMsg(TimeConsumingLog timeConsumingLog) {

        executorService.execute(() -> {
            try {
                template.send(KafkaConstants.TC_LOG_TOPIC, KafkaConstants.TC_LOG_TOPIC_KEY, timeConsumingLog);
            } catch (Exception e) {
                logger.error("记录trace日志到kafka错误!", e);
            }
        });
    }

}
