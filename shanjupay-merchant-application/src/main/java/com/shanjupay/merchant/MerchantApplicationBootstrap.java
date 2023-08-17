package com.shanjupay.merchant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @program: shanjupay
 * @ClassName MerchantApplicationBootstrap
 * @description:
 * @author: wen
 * @create: 2023-08-14 19:31
 * @Version 1.0
 **/
@SpringBootApplication
@EnableSwagger2
public class MerchantApplicationBootstrap {

    /** @Author wen
     * @Description //TODO 
     * @Date 19:31 2023/8/14
     * @Param 
     * @return 
     **/
    public static void main(String[] args) {
        SpringApplication.run(MerchantApplicationBootstrap.class,args);
    }

/** @Author wen
 * @Description //TODO
 * @Date 19:54 2023/8/16
 * @Param
 * @return
 **/
    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate= new RestTemplate(new OkHttp3ClientHttpRequestFactory());

        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();

        messageConverters.set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));

        return restTemplate;
    }
}