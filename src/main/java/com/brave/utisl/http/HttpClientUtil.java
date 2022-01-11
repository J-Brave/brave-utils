package com.brave.utisl.http;

import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @author: J-Brave
 * Date: 2022/1/11
 * Time: 22:28
 * Description:
 */
public class HttpClientUtil {

    /**
    * @Author J-Brave
    * @Description
    * @Date 22:38 2022/1/11
    * @Param 请求方式 - POST  提交方式 - multipart/form-data
    * @return
    **/
    public static String doFormDataPost(String url, String params) {
        try {
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setReadTimeout(30000);
            factory.setConnectTimeout(30000);
            factory.setConnectionRequestTimeout(30000);

            RestTemplate client = new RestTemplate();
            client.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            HttpMethod method = HttpMethod.POST;

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("param", params);

            HttpHeaders headers = new HttpHeaders();
            //设置为表单提交
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            //组装请求头与请求体
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = client.exchange(url, method, entity, String.class);
            if (response.getStatusCode() != HttpStatus.OK) {
                return null;
            }
            return response.getBody();
        } catch (RestClientException e) {
            e.printStackTrace();
            return null;
        }
    }

}
