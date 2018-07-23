package com.stylefeng.roses.kernel.logger.entity;

import lombok.Data;

/**
 * 日志链实体类
 *
 * @author yaoliguo
 * @date 2018-05-16 09:40
 */
@Data
public class TraceLog {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 应用名称
     */
    private String applicationName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 请求路径
     */
    private String servletPath;

    /**
     * rpc调用类型，
     * G1,     //网关发送请求
     * <p>
     * G2,     //接收网关请求（切controller）
     * <p>
     * P1,     //调用端发送请求（切consumer）
     * <p>
     * P2,     //被调用端接收到请求（切provider）
     * <p>
     * P3,     //被调用端发送响应成功
     * <p>
     * P4,     //调用端接收到响应成功
     * <p>
     * EP3,    //被调用端发送响应失败
     * <p>
     * EP4,    //调用端接收到响应失败
     * <p>
     * G3,     //控制器响应网关成功
     * <p>
     * G4,     //网关接收到成功请求
     * <p>
     * EG3,    //控制器接收到错误响应
     * <p>
     * EG4,    //网关接收到错误响应
     */
    private String rpcPhase;

    /**
     * 唯一请求号
     */
    private String traceId;

    /**
     * 节点id
     */
    private String spanId;

    /**
     * 节点父id
     */
    private String parentSpanId;

    /**
     * 生成时间戳
     */
    private Long createTime;

    /**
     * 日志内容
     */
    private String content;

    /**
     * ip地址
     */
    private String ip;

}
