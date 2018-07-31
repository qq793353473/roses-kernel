package com.stylefeng.roses.kernel.logger.config;

import com.stylefeng.roses.kernel.logger.config.properties.LogProperties;
import com.stylefeng.roses.kernel.logger.serizlizer.FastjsonKafkaSerializer;
import com.stylefeng.roses.kernel.logger.service.LogProducerService;
import com.stylefeng.roses.kernel.logger.service.impl.LogProducerServiceImpl;
import com.stylefeng.roses.kernel.model.constants.ConfigPrefixConstants;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

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
    @ConditionalOnProperty(prefix = "spring.kafka", name = "bootstrapServers")
    @ConditionalOnClass(ProducerFactory.class)
    public ProducerFactory<?, ?> kafkaProducerFactory(KafkaProperties properties) {
        DefaultKafkaProducerFactory<String, Object> factory = new DefaultKafkaProducerFactory<>(properties
                .buildProducerProperties());
        factory.setKeySerializer(new StringSerializer());
        factory.setValueSerializer(new FastjsonKafkaSerializer());
        return factory;
    }

    @Bean
    public LogProducerService logProducerService() {
        return new LogProducerServiceImpl();
    }
}