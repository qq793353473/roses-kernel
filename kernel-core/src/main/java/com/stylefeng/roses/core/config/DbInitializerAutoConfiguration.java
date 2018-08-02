package com.stylefeng.roses.core.config;

import com.stylefeng.roses.core.db.listener.InitTableListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库初始化默认配置
 *
 * @author fengshuonan
 * @Date 2018/7/30 下午12:22
 */
@Configuration
public class DbInitializerAutoConfiguration {

    @Bean
    public InitTableListener initDictTableListener() {
        return new InitTableListener();
    }
}


