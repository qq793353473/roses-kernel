package com.stylefeng.roses.core.config;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.stylefeng.roses.core.aop.RequestDataAop;
import com.stylefeng.roses.core.base.controller.GlobalErrorView;
import com.stylefeng.roses.core.converter.RequestDataMessageConvert;
import com.stylefeng.roses.core.util.MvcAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * 默认web配置
 *
 * @author fengshuonan
 * @Date 2018/7/24 下午3:27
 */
@Configuration
public class WebAutoConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    /**
     * 默认错误页面，返回json
     */
    @Bean("error")
    public GlobalErrorView error() {
        return new GlobalErrorView();
    }

    /**
     * 控制器层临时缓存RequestData的aop
     */
    @Bean
    public RequestDataAop requestDataAop() {
        return new RequestDataAop();
    }

    /**
     * RequestData解析器，fastjson的converter
     */
    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter(
            FastJsonHttpMessageConverter fastJsonHttpMessageConverter,
            RequestDataMessageConvert requestDataMessageConvert) {

        return MvcAdapter.requestMappingHandlerAdapter(
                super.requestMappingHandlerAdapter(), fastJsonHttpMessageConverter, requestDataMessageConvert);
    }

    /**
     * RequestData解析器
     */
    @Bean
    public RequestDataMessageConvert requestDataMessageConvert() {
        return new RequestDataMessageConvert();
    }

    /**
     * 时间转化器
     */
    @PostConstruct
    public void addConversionConfig() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        if ((initializer != null ? initializer.getConversionService() : null) != null) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
            genericConversionService.addConverter(new StringToDateConverter());
        }
    }

    public class StringToDateConverter implements Converter<String, Date> {
        @Override
        public Date convert(String dateString) {
            return DateUtil.parse(dateString);
        }
    }
}


