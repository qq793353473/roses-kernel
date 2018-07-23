package com.stylefeng.roses.kernel.sign.config.properties;

import lombok.Data;

/**
 * 签名校验需要的配置
 *
 * @author fengshuonan
 * @Date 2018/5/8 下午2:24
 */
@Data
public class SignProperties {

    /**
     * 签名失效时间
     */
    private long time;

}