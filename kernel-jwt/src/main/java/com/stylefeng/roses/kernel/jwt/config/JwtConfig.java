package com.stylefeng.roses.kernel.jwt.config;

import com.stylefeng.roses.kernel.jwt.properties.JwtProperties;
import com.stylefeng.roses.kernel.jwt.utils.JwtTokenUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * jwt自动配置
 *
 * @author fengshuonan
 * @date 2018-02-08 9:49
 */
@Configuration
public class JwtConfig {

    /**
     * jwt token的配置
     */
    @Bean
    @ConfigurationProperties(prefix = "jwt")
    public JwtProperties jwtProperties() {
        return new JwtProperties();
    }

    /**
     * jwt工具类
     */
    @Bean
    public JwtTokenUtil jwtTokenUtil(JwtProperties jwtProperties) {
        return new JwtTokenUtil(jwtProperties.getSecret(), jwtProperties.getExpiration());
    }
}

