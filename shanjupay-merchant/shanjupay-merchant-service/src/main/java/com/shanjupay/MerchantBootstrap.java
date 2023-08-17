package com.shanjupay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.management.Agent;

/**
 * @program: shanjupay
 * @ClassName MerchantBootstrap
 * @description:
 * @author: wen
 * @create: 2023-08-15 19:11
 * @Version 1.0
 **/
@SpringBootApplication
public class MerchantBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(MerchantBootstrap.class,args);
    }
}