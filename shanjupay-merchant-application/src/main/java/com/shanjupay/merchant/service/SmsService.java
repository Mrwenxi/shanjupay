package com.shanjupay.merchant.service;

/**
 * @program: shanjupay
 * @ClassName SmsService
 * @description:
 * @author: wen
 * @create: 2023-08-17 09:10
 * @Version 1.0
 **/
public interface SmsService {

    //ee
    /** @Author wen
     * @Description //TODO 
     * @Date 9:11 2023/8/17
     * @Param 
     * @return 
     **/
    String sendMsg(String phone);

    /** @Author wen
     * @Description //TODO 
     * @Date 19:57 2023/8/17
     * @Param 
     * @return 
     **/
    void checkverifycode(String verifyKey,String verifyCode);
}