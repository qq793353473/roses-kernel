package com.stylefeng.roses.core.context;

import com.stylefeng.roses.core.util.HttpContext;
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

        try {

            HttpServletRequest request = HttpContext.getRequest();
            String spanId = request.getHeader(RosesConstants.SPAN_ID_HEADER_NAME);

            if (spanId == null) {
                return "";
            } else {
                return spanId;
            }

        } catch (NullPointerException e) {
            log.debug("没有spanId !");
            return "";
        }

    }

}
