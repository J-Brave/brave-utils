package com.brave.utils.http;

import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

/**
 * @author jbrave
 */
public class HttpClientUtil {

    /**
     * POST请求 application/x-www-from-urlencoded
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, MultiValueMap<String, Object> params) {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(30000);
        httpRequestFactory.setConnectTimeout(30000);
        httpRequestFactory.setReadTimeout(30000);
        httpRequestFactory.setBufferRequestBody(false);
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, method, requestEntity, String.class);
        if (HttpStatus.OK != response.getStatusCode()) {
            return null;
        }
        return response.getBody();
    }

    public static String doPost(String url, MultipartFile file) {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(30000);
        httpRequestFactory.setConnectTimeout(30000);
        httpRequestFactory.setReadTimeout(30000);
        httpRequestFactory.setBufferRequestBody(false);
        return null;
    }

}
