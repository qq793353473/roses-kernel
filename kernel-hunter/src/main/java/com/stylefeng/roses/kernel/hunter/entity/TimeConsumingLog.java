package com.stylefeng.roses.kernel.hunter.entity;

import lombok.Data;

import java.util.Date;

/**
 * 请求时间记录日志
 *
 * @author fengshuonan
 * @Date 2018/7/16 下午5:13
 */
@Data
public class TimeConsumingLog {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 请求路径
     */
    private String requestPath;

    /**
     * 花费时间（毫秒）
     */
    private Long useTime;

    /**
     * 创建时间
     */
    private Date createTime;

}
