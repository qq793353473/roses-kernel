package cn.stylefeng.roses.http.util;


import cn.stylefeng.roses.kernel.logger.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Map;


@Service
public class NetPost {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * POST application/json 请求
     *
     * @param url          请求地址
     * @param json         请求 JSON 数据
     * @param responseType 返回泛型对象类型
     * @param <T>
     * @return
     */
    public <T> T postJson(String url, String json, Class<T> responseType) {
        return post(url, json, null, responseType);
    }

    /**
     * POST application/json 请求  带Headers 自定义头部信息
     *
     * @param url          请求地址
     * @param json         请求 JSON 数据
     * @param httpHeaders  自定义头部
     * @param responseType 返回泛型对象类型
     * @param <T>
     * @return
     */
    public <T> T postJson(String url, String json, Map<String, String> httpHeaders, Class<T> responseType) {
        return post(url, json, httpHeaders, responseType);
    }

    /**
     * POST x-www-form-urlencoded 请求  带Headers 自定义头部信息
     *
     * @param url          请求地址
     * @param formData     请求数据
     * @param httpHeaders  自定义头部
     * @param responseType 返回泛型对象类型
     * @param <T>
     * @return
     */
    public <T> T postForm(String url, String formData, Map<String, String> httpHeaders, Class<T> responseType) {
        LogUtil.info(url + "->入参数->：" + formData);
        restTemplate.getMessageConverters().set(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded");
        headers.setContentType(type);
        if (httpHeaders != null) {
            for (Map.Entry<String, String> entry : httpHeaders.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<String> entity = new HttpEntity<String>(formData, headers);
        T tokenStr = restTemplate.postForObject(url, entity, responseType);
        LogUtil.info(url + "->返回参数->：" + tokenStr);
        return tokenStr;
    }

    /**
     * GET 请求
     *
     * @param url          请求地址
     * @param parmars      参数
     * @param responseType 返回泛型对象类型
     * @param <T>
     * @return
     */
    public <T> T get(String url, Map<String, ?> parmars, Class<T> responseType) {
        return get(url, parmars, null, responseType);
    }

    /**
     * GET 请求 带Headers 自定义头部信息
     *
     * @param url          请求地址
     * @param parmars      参数
     * @param httpHeaders  自定义头部
     * @param responseType 返回泛型对象类型
     * @param <T>
     * @return
     */
    public <T> T get(String url, Map<String, ?> parmars, Map<String, String> httpHeaders, Class<T> responseType) {
        LogUtil.info(url + "->入参数->：" + parmars);
        restTemplate.getMessageConverters().set(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        HttpHeaders headers = new HttpHeaders();
        if (httpHeaders != null) {
            for (Map.Entry<String, String> entry : httpHeaders.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<T> tokenStr = restTemplate.exchange(url, HttpMethod.GET, entity, responseType, parmars);
        LogUtil.info(url + "->返回参数->：" + tokenStr);
        return tokenStr.getBody();
    }


    private <T> T post(String url, String json, Map<String, String> httpHeaders, Class<T> responseType) {
        LogUtil.info(url + "->入参数->：" + json);
        restTemplate.getMessageConverters().set(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);
        if (httpHeaders != null) {
            for (Map.Entry<String, String> entry : httpHeaders.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        T tokenStr = restTemplate.postForObject(url, entity, responseType);
        LogUtil.info(url + "->返回参数->：" + tokenStr);
        return tokenStr;
    }
}
