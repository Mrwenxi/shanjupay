package com.shanjupay.merchant.controller;

import com.shanjupay.merchant.api.MerchantService;
import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: shanjupay
 * @ClassName MerchantController
 * @description:
 * @author: wen
 * @create: 2023-08-16 09:03
 * @Version 1.0
 **/
@RestController
@Api(value="商户平台接口",tags = "商户平台接口",description = "商户平台 接口")
public class MerchantController {

    @Reference
    MerchantService merchantService;

    @Autowired
    SmsService smsService;


    @ApiOperation(value="根据id查询商户信息")
    @GetMapping("/merchants/{id}")
    public MerchantDTO querymerchantbyid(
            @ApiParam(value = "商户id ")
            @PathVariable Long id){
        MerchantDTO querymerchantbyid = merchantService.querymerchantbyid(id);
        return querymerchantbyid;
    }


    @ApiOperation("获取验证码")
    @ApiImplicitParam(name = "phone",value = "手机号码",required = true,dataType = "String",paramType = "query")
    @GetMapping("/sms")
    public String getsmscode(String phone){

        String key  = smsService.sendMsg(phone);
    return key;
    }





}