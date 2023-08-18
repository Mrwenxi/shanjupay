package com.shanjupay.merchant.service;

import com.shanjupay.merchant.common.domain.BusinessException;
import com.shanjupay.merchant.common.domain.CommonErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
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
        body.put("mobile",phone);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String,Object>> httpEntity = new HttpEntity<>(body,httpHeaders);


        Map bodymap = null;
        try {
            ResponseEntity<Map> exchange = restTemplate.exchange(sms_url, HttpMethod.POST, httpEntity, Map.class);

            bodymap = exchange.getBody();
            System.out.println(bodymap);
        }catch (RestClientException e){
            e.printStackTrace();
            throw new BusinessException(CommonErrorCode.E_100102);

//            throw  new RuntimeException("sb");
        }
        if(bodymap ==null || bodymap.get("result")==null)
            throw new BusinessException(CommonErrorCode.E_100102);

//            throw new RuntimeException("sb");


        Map map = (Map) bodymap.get("result");
        String key = (String) map.get("key");
        System.out.println(key);
        return  key;
    }


    @Override
    public void checkverifycode(String verifyKey, String verifyCode) {

        //校验验证码的url
        String sms_url = "http://localhost:56085/sailing/verify?name=sms&verificationCode="+verifyCode+"&verificationKey="+verifyKey;

        Map bodyMap = null;
        try {
            //使用restTemplate请求验证码服务
            ResponseEntity<Map> exchange = restTemplate.exchange(sms_url, HttpMethod.POST, HttpEntity.EMPTY, Map.class);
//            log.info("请求验证码服务，得到响应:{}", JSON.toJSONString(exchange));
            bodyMap = exchange.getBody();
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException(CommonErrorCode.E_100102);
//            throw new RuntimeException("校验验证码失败");

        }
        if(bodyMap == null || bodyMap.get("result") == null || !(Boolean) bodyMap.get("result")){
            throw new BusinessException(CommonErrorCode.E_100102);
        }

    }
}