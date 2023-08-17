package com.shanjupay.merchant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: shanjupay
 * @ClassName SmsServiceImpl
 * @description:
 * @author: wen
 * @create: 2023-08-17 09:11
 * @Version 1.0
 **/
@Service
public class SmsServiceImpl implements SmsService{

    @Autowired
    RestTemplate restTemplate;

    @Value("${sms.url}")
    String url;

    @Value("${sms.effectiveTime}")
    String effectiveTime;



    @Override
    public String sendMsg(String phone) {
        String sms_url = url+"/generate?name=sms&effectiveTime="+effectiveTime;

        Map<String,Object> body = new HashMap<>();
        body.put("monile",phone);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String,Object>> httpEntity = new HttpEntity<>(body,httpHeaders);
        ResponseEntity<Map> exchange = restTemplate.exchange(sms_url, HttpMethod.POST,httpEntity,Map.class);

        Map bodymap = exchange.getBody();
        System.out.println(bodymap);

        Map map = (Map) bodymap.get("result");
        String key = (String) map.get("key");
        System.out.println(key);
        return  key;
    }
}