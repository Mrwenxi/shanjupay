package com.shanjupay.merchant.controller;

import com.shanjupay.merchant.api.MerchantService;
import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.common.domain.BusinessException;
import com.shanjupay.merchant.common.domain.CommonErrorCode;
import com.shanjupay.merchant.common.util.PhoneUtil;
import com.shanjupay.merchant.convert.MerchantRegisterConvert;
import com.shanjupay.merchant.service.SmsService;
import com.shanjupay.merchant.vo.MerchantRegisterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("商户注册")
    @ApiImplicitParam(name = "MerchantRegisterVo",value = "注册商户信息",required = true,
    dataType = "MerchantRegisterVo",paramType = "body")
    @PostMapping("/merchants/register")
    public MerchantRegisterVo merchantRegisterVo(@RequestBody MerchantRegisterVo merchantRegisterVo){

        //校验参数的合法性
        if(merchantRegisterVo == null){
            throw new BusinessException(CommonErrorCode.E_100108);
        }
        if(StringUtils.isBlank(merchantRegisterVo.getMobile())){
            throw new BusinessException(CommonErrorCode.E_100112);
        }
        //手机号格式校验
        if(!PhoneUtil.isMatches(merchantRegisterVo.getMobile())){
            throw new BusinessException(CommonErrorCode.E_100109);
        }




        smsService.checkverifycode(merchantRegisterVo.getVerifyKey(),merchantRegisterVo.getVerifyCode());

/*        MerchantDTO merchantDTO = new MerchantDTO();
        merchantDTO.setUsername(merchantRegisterVo.getUsername());
        merchantDTO.setMobile(merchantDTO.getMobile());
        merchantService.createMerchant(merchantDTO);*/

        MerchantDTO merchantDTO = MerchantRegisterConvert.INSTANCE.vo2dto(merchantRegisterVo);

        merchantService.createMerchant(merchantDTO);



//
        //校验验证码
//        smsService.checkverifycode(merchantRegisterVo.getVerifyKey(),merchantRegisterVo.getVerifyCode());
        //调用dubbo服务接口
//        MerchantDTO merchantDTO = new MerchantDTO();
        //向dto写入商户注册的信息
//        merchantDTO.setMobile(merchantRegisterVO.getMobile());
//        merchantDTO.setUsername(merchantRegisterVO.getUsername());
        //...
        //使用MapStruct转换对象
//        MerchantDTO merchantDTO = MerchantRegisterConvert.INSTANCE.vo2dto(merchantRegisterVO);
//        merchantService.createMerchant(merchantDTO);
//        return merchantRegisterVO;




         return merchantRegisterVo;
    }





}