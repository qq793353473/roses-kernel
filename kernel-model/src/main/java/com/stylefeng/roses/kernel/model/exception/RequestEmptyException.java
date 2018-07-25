package com.stylefeng.roses.kernel.model.exception;

import lombok.Data;

/**
 * 业务异常的封装
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:05:10
 */
@Data
public class RequestEmptyException extends RuntimeException {

    private Integer code;

    private String errorMessage;

    public RequestEmptyException() {
        super("请求数据不完整或格式错误！");
        this.code = 400;
        this.errorMessage = "请求数据不完整或格式错误！";
    }

    public RequestEmptyException(String errorMessage) {
        super(errorMessage);
        this.code = 400;
        this.errorMessage = errorMessage;
    }

    /**
     * 不拷贝栈信息，提高性能
     *
     * @author fengshuonan
     * @Date 2018/7/25 下午1:48
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }
}
