package com.stylefeng.roses.core.context;

import com.stylefeng.roses.core.util.HttpContext;
import com.stylefeng.roses.core.util.ToolUtil;
import com.stylefeng.roses.kernel.model.constants.RosesConstants;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取当前请求的请求号，没有请求号则生成空串
 *
 * @author fengshuonan
 * @date 2018-05-09-下午6:25
 */
@Slf4j
public class RequestNoContext {

    public static String getRequestNo() {
        HttpServletRequest request = HttpContext.getRequest();

        if (request == null) {
            if (log.isDebugEnabled()) {
                log.info("获取请求号失败，当前不是http请求环境！");
            }
            return "";
        } else {
            String requestNo = request.getHeader(RosesConstants.REQUEST_NO_HEADER_NAME);
            if (ToolUtil.isEmpty(requestNo)) {
                return "";
            } else {
                return requestNo;
            }
        }
    }

}
