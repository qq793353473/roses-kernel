package com.stylefeng.roses.kernel.logger.config;

import com.stylefeng.roses.kernel.logger.config.properties.LogProperties;
import com.stylefeng.roses.kernel.logger.service.LogProducerService;
import com.stylefeng.roses.kernel.logger.service.impl.LogProducerServiceImpl;
import com.stylefeng.roses.kernel.model.constants.ConfigPrefixConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 默认kafka消息队列日志
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午4:48:10
 */
@Configuration
public class LoggerAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = ConfigPrefixConstants.LOG_PREFIX)
    public LogProperties logProperties() {
        return new LogProperties();
    }

    @Bean
    public LogProducerService logProducerService() {
        return new LogProducerServiceImpl();
    }
}