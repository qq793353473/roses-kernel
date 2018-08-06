package com.stylefeng.roses.kernel.model.exception;

import lombok.Getter;

/**
 * 远程接口调用出现的业务异常
 *
 * @author fengshuonan
 * @date 2018-08-06-上午11:33
 */
@Getter
public class ApiServiceException extends Exception {

    /**
     * 错误编码
     */
    private Integer code;

    /**
     * 错误的提示信息
     */
    private String errorMessage;

    public ApiServiceException(AbstractBaseExceptionEnum exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.errorMessage = exception.getMessage();
    }
}
