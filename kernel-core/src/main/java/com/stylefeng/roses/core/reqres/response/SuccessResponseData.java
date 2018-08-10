package com.stylefeng.roses.core.reqres.response;

/**
 * 请求成功的返回
 *
 * @author stylefeng
 * @Date 2018/1/4 22:38
 */
public class SuccessResponseData extends ResponseData {

    public SuccessResponseData() {
        super(true, ResponseData.DEFAULT_SUCCESS_CODE, ResponseData.DEFAULT_SUCCESS_MESSAGE, null);
    }

    public SuccessResponseData(Object object) {
        super(true, ResponseData.DEFAULT_SUCCESS_CODE, ResponseData.DEFAULT_SUCCESS_MESSAGE, object);
    }

    public SuccessResponseData(Integer code, String message, Object object) {
        super(true, code, message, object);
    }
}
