/**
 * Copyright 2018-2020 stylefeng & fengshuonan (sn93@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.roses.core.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 快捷获取HttpServletRequest,HttpServletResponse
 *
 * @author stylefeng
 * @Date 2018/1/4 21:24
 */
public class HttpContext {
    public HttpContext() {
    }

    public static String getIp() {
        HttpServletRequest request = getRequest();
        return request == null ? "127.0.0.1" : request.getRemoteHost();
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getResponse();
    }

    public static Map<String, String> getRequestParameters() {
        HashMap<String, String> values = new HashMap();
        HttpServletRequest request = getRequest();
        if (request == null) {
            return values;
        } else {
            Enumeration enums = request.getParameterNames();

            while(enums.hasMoreElements()) {
                String paramName = (String)enums.nextElement();
                String paramValue = request.getParameter(paramName);
                values.put(paramName, paramValue);
            }

            return values;
        }
    }

    public static Cookie getCookie(String name) {
        HttpServletRequest request = getRequest();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            Cookie[] var3 = cookies;
            int var4 = cookies.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Cookie cookie = var3[var5];
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }

        return null;
    }

    public static String getRequestToken() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        } else {
            String authorization = request.getHeader("Authorization");
            if (ToolUtil.isNotEmpty(authorization)) {
                return authorization;
            } else {
                String token = request.getParameter("token");
                if (ToolUtil.isNotEmpty(token)) {
                    return token;
                } else {
                    Cookie cookie = getCookie("token");
                    return cookie != null ? cookie.getValue() : null;
                }
            }
        }
    }
}
