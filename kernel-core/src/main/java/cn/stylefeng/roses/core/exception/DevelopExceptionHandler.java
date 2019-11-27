//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stylefeng.roses.core.exception;

import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.HttpContext;
import cn.stylefeng.roses.kernel.model.exception.ApiServiceException;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.exception.enums.CoreExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Order(200)
public class DevelopExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(DevelopExceptionHandler.class);

    public DevelopExceptionHandler() {
    }

    @ExceptionHandler({ApiServiceException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData apiService(ApiServiceException e) {
        log.error("服务具体异常:", e);
        ErrorResponseData errorResponseData = this.getErrorResponse(e.getCode(), e.getErrorMessage());
        errorResponseData.setExceptionClazz(e.getExceptionClassName());
        return errorResponseData;
    }

    @ExceptionHandler({RequestEmptyException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData emptyRequest(RequestEmptyException e) {
        return this.getErrorResponse(e.getCode(), e.getErrorMessage());
    }

    @ExceptionHandler({ServiceException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData notFount(ServiceException e) {
        log.info("业务异常:", e);
        return this.getErrorResponse(e.getCode(), e.getErrorMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData serverError(Exception e) {
        log.error("运行时异常:", e);
        return this.getErrorResponse(CoreExceptionEnum.SERVICE_ERROR.getCode(), e.getMessage());
    }

    private ErrorResponseData getErrorResponse(Integer code, String message) {
        ErrorResponseData error = new ErrorResponseData(code, "requestNo:" + HttpContext.getRequestNo() + "," + message);
        return error;
    }
}
