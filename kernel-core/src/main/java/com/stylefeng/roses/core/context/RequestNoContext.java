package com.stylefeng.roses.core.context;

import com.stylefeng.roses.core.util.HttpContext;
import com.stylefeng.roses.kernel.model.constants.RosesConstants;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求号上下文
 *
 * @author fengshuonan
 * @date 2018-05-09-下午6:25
 */
@Slf4j
public class RequestNoContext {

    /**
     * 获取当前请求的请求号，没有请求号则生成空串
     *
     * @author fengshuonan
     * @Date 2018/5/9 下午6:26
     */
    public static String getRequestNo() {

        try {

            HttpServletRequest request = HttpContext.getRequest();
            String requestNo = request.getHeader(RosesConstants.REQUEST_NO_HEADER_NAME);

            if (requestNo == null) {
                return "";
            } else {
                return requestNo;
            }

        } catch (NullPointerException e) {
            log.debug("没有请求号！");
            return "";
        }

    }

}
