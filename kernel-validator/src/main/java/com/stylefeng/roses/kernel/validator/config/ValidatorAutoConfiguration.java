package com.stylefeng.roses.kernel.validator.config;

import com.stylefeng.roses.kernel.validator.aop.ParamValidateAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 签名的自动配置
 *
 * @author fengshuonan
 * @date 2018-07-23-下午5:55
 */
@Configuration
public class ValidatorAutoConfiguration {

    @Bean
    public ParamValidateAop paramValidateAop() {
        return new ParamValidateAop();
    }
}
