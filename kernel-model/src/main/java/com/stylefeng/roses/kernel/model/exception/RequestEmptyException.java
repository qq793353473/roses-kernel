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

    public RequestEmptyException(String errorMessage) {
        super(errorMessage);
        this.code = 400;
        this.errorMessage = errorMessage;
    }
}
