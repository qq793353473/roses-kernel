package com.stylefeng.roses.kernel.hunter.chain.context;

import com.stylefeng.roses.core.util.HttpContext;
import com.stylefeng.roses.core.util.ToolUtil;
import com.stylefeng.roses.kernel.model.constants.RosesConstants;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * header中的spanId的上下文,获取上个请求的spanId，和holder的区别是，holder放的是本应用的spanId
 *
 * @author fengshuonan
 * @date 2018-05-09-下午6:25
 */
@Slf4j
public class SpanIdContext {

    public static String getSpanId() {
        HttpServletRequest request = HttpContext.getRequest();

        if (request == null) {
            if (log.isDebugEnabled()) {
                log.info("获取spanId失败，当前不是http请求环境！");
            }
            return "";
        } else {
            String requestNo = request.getHeader(RosesConstants.SPAN_ID_HEADER_NAME);
            if (ToolUtil.isEmpty(requestNo)) {
                return "";
            } else {
                return requestNo;
            }
        }
    }

}
