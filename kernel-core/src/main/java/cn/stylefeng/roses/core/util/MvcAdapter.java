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

import cn.stylefeng.roses.core.converter.RequestDataMessageConvert;
import cn.stylefeng.roses.core.converter.RequestDataTypeMethodProcessor;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 统一请求转化器默认配置
 *
 * @author fengshuonan
 * @Date 2018/2/24 14:39
 */
public class MvcAdapter {

    public MvcAdapter(){

    }

    public static RequestMappingHandlerAdapter requestMappingHandlerAdapter(
            RequestMappingHandlerAdapter original,
            FastJsonHttpMessageConverter fastConverter,
            RequestDataMessageConvert requestDataMessageConvert) {

        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(requestDataMessageConvert);

        List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<>();
        argumentResolvers.add(new RequestDataTypeMethodProcessor(converters));
        original.setCustomArgumentResolvers(argumentResolvers);

        List<HttpMessageConverter<?>> list = new LinkedList<>();

        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setWriteAcceptCharset(false);  // see SPR-7316
        list.add(stringHttpMessageConverter);

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializeFilters((ValueFilter) (o, s, source) -> {
//            if (source == null) {
//                return "";
//            }
//            if (source instanceof Date) {
//                return DateUtil.formtString((Date) source, "yyyy-MM-dd HH:mm:ss");
//            }
//            return source;
//        });
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        // 处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        //在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);

        list.add(fastConverter);
        original.setMessageConverters(list);
        return original;
    }
}