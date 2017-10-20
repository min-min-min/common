package com.banggood.common.http;

import com.banggood.common.entity.Result;
import com.banggood.common.utils.ResultUtil;
import com.google.common.collect.Maps;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

    private static final String JSON_HEADER = "application/json; charset=UTF-8";

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
     * @return {@link Result} 200成功  500失败
     */
    public <T> Result<T> get(@NonNull String url, Map<String, ?> paramMap, @NonNull Class<T> clazz, Map<String, String> headerMap) {
        String paramString = "";
        Result result;
        if (null != paramMap && paramMap.size() > 0) {
            StringBuilder paramStringBuilder = new StringBuilder();
            paramMap.forEach((k, v) -> paramStringBuilder.append("&").append(k).append("=").append("{" + k + "}"));
            paramString = "?" + paramStringBuilder.toString().substring(1);
        } else {
            paramMap = Maps.newHashMap();
        }
        log.debug("请求的url=>{},参数=>{}", url + paramString, paramMap.toString());
        HttpHeaders headers = new HttpHeaders();
        if (null != headerMap && headerMap.size() > 0) {
            headerMap.forEach(headers::add);
        }
        log.debug("请求携带头部=>{}", headers.toString());
        HttpEntity requestEntity = new HttpEntity<>(null, headers);
        try {
            result = ResultUtil.success(restTemplate.exchange(url + paramString, HttpMethod.GET, requestEntity, clazz, paramMap).getBody());
        } catch (Exception e) {
            e.printStackTrace();
            result = ResultUtil.error(e.getMessage());
        }
        return result;
    }

    /**
     * TODO
     *
     * @param url
     * @param paramMap
     * @param clazz
     * @param headerMap
     * @return
     */
    public Result postForm(@NonNull String url, Map<String, ?> paramMap, @NonNull Class clazz, @NonNull Map<String, String> headerMap) {
        return null;
    }

    /**
     * TODO
     *
     * @param url
     * @param param
     * @param clazz
     * @param headerMap
     * @return
     */
    public Result postJson(@NonNull String url, String param, @NonNull Class clazz, @NonNull Map<String, String> headerMap) {
        return null;
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        RestRequest restRequest = new RestRequest(restTemplate);
        Map<String, Object> map = Maps.newHashMap();
        map.put("channelName", "DHL");
        Map<String, String> head = Maps.newHashMap();
        head.put("Authorization", "@$%s5jhk36@!#Sap");
        Result a = restRequest.get("http://203.88.208.20:9001/db/channel", map, String.class, head);
        System.out.println(a.toString());
    }
}
