package com.stylefeng.roses.kernel.hunter.entity;

import lombok.Data;

/**
 * 日志实体类
 *
 * @author yaoliguo
 * @date 2018-04-24 13:45
 */
@Data
public class LoggerContent {

    private static final long serialVersionUID = 1L;

    /**
     * 日志id
     */
    private String logId;

    /**
     * 应用id
     */
    private String appId;

    /**
     * spring.application.name的名称
     */
    private String applicationName;

    /**
     * 应用名称
     */
    private String className;

    /**
     * 服务端内网IP
     */
    private String ip;

    /**
     * 账号id
     */
    private String accountId;

    /**
     * 日志号
     */
    private String logNum;

    /**
     * 日志名称
     */
    private String url;

    /**
     * 请求地址
     */
    private String requestData;

    /**
     * 日志类别
     */
    private String logCategory;

    /**
     * 日志内容简要
     */
    private String logContent;

    /**
     * 日志详情
     */
    private String logDetails;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * Y:已删除   N:未删除
     */
    private String delFlag;

}
