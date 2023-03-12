package com.toyproject.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.toyproject.util.jwt.JwtProvider;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Optional;

public class RestTemplateUtils {

    private RestTemplateUtils(){}

    private static HttpHeaders getApplicationJsonHeaders(String authKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        if (!StringUtil.isBlankString(authKey)) {
            if (JwtProvider.isBearer(authKey)) {
                headers.setBearerAuth(authKey.substring(7));
            } else {
                headers.setBearerAuth(authKey);
            }
        }
        return headers;
    }

    public static Object getJsonRequest(String url) throws URISyntaxException, ParseException {
        return getJsonRequest(url, null);
    }

    public static Object getJsonRequest(String url, String token) throws URISyntaxException, ParseException {
        Object result = null;

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpHeaders headers = getApplicationJsonHeaders(token);
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(headers);
        URI uri = new URI(url);
        ResponseEntity<String> responseEntity
                = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        Optional<String> body = Optional.ofNullable(responseEntity.getBody());

        if (body.isPresent()) {
            JSONParser parser = new JSONParser();
            result = parser.parse(new String(body.get().getBytes(StandardCharsets.UTF_8)));
        }


        return result;
    }
}
