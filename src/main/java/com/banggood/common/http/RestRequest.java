package com.banggood.common.http;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * RestTemplate 请求接口
 * Created by Chenjing on 2017/10/19.
 *
 * @author Chenjing
 */
@Slf4j
public class RestRequest {

    private RestTemplate restTemplate;

    private static final String CONTENT_TYPE = "Content-Type";

    private static final String APPLICATION_JSON = "application/json";

    private static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";

    public RestRequest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * get 请求
     *
     * @param url       url
     * @param paramMap  参数 如果没有可以为null
     * @param clazz     输出类型
     * @param headerMap 头部map 如果没有 可以为null
     * @return {@link ResponseEntity}
     */
    public <T> ResponseEntity<T> get(@NonNull String url, Map<String, ?> paramMap, @NonNull Class<T> clazz,
                                     Map<String, String> headerMap) throws RestClientException {
        String paramString = "";
        if (null != paramMap && paramMap.size() > 0) {
            StringBuilder paramStringBuilder = new StringBuilder();
            paramMap.forEach((k, v) -> paramStringBuilder.append("&").append(k).append("=").append("{").append(k).append("}"));
            paramString = "?" + paramStringBuilder.toString().substring(1);
        } else {
            paramMap = Maps.newHashMap();
        }
        log.info("请求的url=>{},参数=>{}", url + paramString, paramMap.toString());
        HttpHeaders headers = new HttpHeaders();
        if (null != headerMap && headerMap.size() > 0) {
            headerMap.forEach(headers::add);
        }
        log.debug("请求携带头部=>{}", headers.toString());
        HttpEntity requestEntity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(url + paramString, HttpMethod.GET, requestEntity, clazz, paramMap);
    }

    /**
     * post 表单请求
     *
     * @param url       url
     * @param paramMap  map参数 如果没有 可以为null
     * @param clazz     输出类型class
     * @param headerMap 请求头 如果没有 可以为null
     * @return {@link ResponseEntity}
     */
    public <T> ResponseEntity<T> postForm(@NonNull String url, Map<String, Object> paramMap, @NonNull Class<T> clazz,
                                          Map<String, String> headerMap) throws RestClientException {
        LinkedMultiValueMap<String, Object> linkedMultiValueMap = new LinkedMultiValueMap<>();
        if (null != paramMap && paramMap.size() > 0) {
            paramMap.forEach(linkedMultiValueMap::add);
        }
        log.info("请求的url=>{},参数=>{}", url, linkedMultiValueMap.toString());
        HttpHeaders headers = new HttpHeaders();
        headerMap.putIfAbsent(CONTENT_TYPE, APPLICATION_FORM_URLENCODED);
        headerMap.forEach(headers::add);
        log.debug("请求携带头部=>{}", headers.toString());
        HttpEntity<LinkedMultiValueMap<String, Object>> httpEntity = new HttpEntity<>(linkedMultiValueMap, headers);
        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, clazz, paramMap);
    }

    /**
     * post json请求
     *
     * @param url       url
     * @param param     json 字符串
     * @param clazz     输出类型class
     * @param headerMap http请求头 如果没有可以为null
     * @return {@link ResponseEntity}
     */
    public <T> ResponseEntity<T> postJson(@NonNull String url, String param, @NonNull Class<T> clazz, Map<String, String> headerMap) {
        if (null == param) {
            param = "";
        }
        log.info("请求的url=>{},参数=>{}", url, param);
        HttpHeaders headers = new HttpHeaders();
        headerMap.putIfAbsent(CONTENT_TYPE, APPLICATION_JSON);
        headerMap.forEach(headers::add);
        log.debug("请求携带头部=>{}", headers.toString());
        HttpEntity<String> httpEntity = new HttpEntity<>(param, headers);
        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, clazz, param);
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        RestRequest restRequest = new RestRequest(restTemplate);
        Map<String, Object> map = Maps.newHashMap();
        map.put("channelName", "DHL");
        Map<String, String> head = Maps.newHashMap();
        head.put("Authorization", "@$%s5jhk36@!#Sap");
        ResponseEntity<String> a = restRequest.get("http://203.88.208.20:9001/db/channel", map, String.class, head);
        System.out.println(a.toString());


        LinkedMultiValueMap<String, Object> linkedMultiValueMap = new LinkedMultiValueMap<>();
        linkedMultiValueMap.add("typeName", "test");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("typeName", "test");
        ResponseEntity<String> b = restRequest.postJson("http://192.168.1.58:9001/printtype", jsonObject.toJSONString(), String.class, head);
        System.out.println(b.getBody());


    }
}
