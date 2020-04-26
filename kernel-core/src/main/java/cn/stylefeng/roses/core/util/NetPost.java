package cn.stylefeng.roses.core.util;


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

    public <T> T reqJsonPost(String url, String json, Class<T> responseType) {
        return reqJsonPost(url, json, null, responseType);
    }

    public <T> T reqJsonPost(String url, String json, Map<String, String> httpHeaders, Class<T> responseType) {
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

    public <T> T reqFormPost(String url, String formData, Map<String, String> httpHeaders, Class<T> responseType) {
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

    public <T> T reqGet(String url, Map<String, String> httpHeaders, Map<String, ?> parmars, Class<T> responseType) {
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
}
