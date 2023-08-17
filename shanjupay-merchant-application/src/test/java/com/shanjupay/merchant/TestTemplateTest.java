package com.shanjupay.merchant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: shanjupay
 * @ClassName TestTemplateTest
 * @description:
 * @author: wen
 * @create: 2023-08-16 19:53
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTemplateTest {

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void getbaidu(){
        String url = "http://www.baidu.com";
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url,String.class);
        String body = forEntity.getBody();
        System.out.println(body);
    }

    @Test
    public void getsmscode(){
        String url = "http://localhost:56085/sailing/generate?effectiveTime=3000&name=sms";

        Map<String,Object> body = new HashMap<>();
        body.put("monile","123123");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String,Object>> httpEntity = new HttpEntity<>(body,httpHeaders);
        ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.POST,httpEntity,Map.class);

        Map bodymap = exchange.getBody();
        System.out.println(bodymap);

        Map map = (Map) bodymap.get("result");
        String key = (String) map.get("key");
        System.out.println(key);


    }

}