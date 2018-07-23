package com.stylefeng.roses.kernel.logger.config.properties;

import lombok.Data;

/**
 * 日志记录的参数配置
 *
 * @author fengshuonan
 * @Date 2018/5/26 下午2:16
 */
@Data
public class LogProperties {

    /**
     * 记录日志的级别（逗号隔开）
     */
    private String level = "error,info";

    /**
     * 是否开启trace链式记录
     */
    private Boolean trace = true;

}
