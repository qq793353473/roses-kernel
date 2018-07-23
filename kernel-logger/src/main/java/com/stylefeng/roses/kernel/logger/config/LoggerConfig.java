package com.stylefeng.roses.kernel.logger.config;

import com.stylefeng.roses.kernel.logger.config.properties.LogProperties;
import com.stylefeng.roses.kernel.model.constants.ConfigPrefixConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志记录的自动配置
 *
 * @author fengshuonan
 * @date 2018-07-23-下午5:38
 */
@Configuration
public class LoggerConfig {

    @Bean
    @ConfigurationProperties(prefix = ConfigPrefixConstants.LOG_PREFIX)
    public LogProperties logProperties() {
        return new LogProperties();
    }
}
