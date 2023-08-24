package com.shanjupay.merchant.controller;

import com.shanjupay.merchant.api.AppService;
import com.shanjupay.merchant.api.MerchantService;
import com.shanjupay.merchant.api.dto.AppDTO;
import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.common.domain.BusinessException;
import com.shanjupay.merchant.common.domain.CommonErrorCode;
import com.shanjupay.merchant.common.util.PhoneUtil;
import com.shanjupay.merchant.common.util.SecurityUtil;
import com.shanjupay.merchant.convert.MerchantDetailConvert;
import com.shanjupay.merchant.convert.MerchantRegisterConvert;
import com.shanjupay.merchant.entity.Merchant;
import com.shanjupay.merchant.service.FileService;
import com.shanjupay.merchant.service.SmsService;
import com.shanjupay.merchant.vo.MerchantDetailVO;
import com.shanjupay.merchant.vo.MerchantRegisterVo;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ReactiveTypeDescriptor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @program: shanjupay
 * @ClassName MerchantController
 * @description:
 * @author: wen
 * @create: 2023-08-16 09:03
 * @Version 1.0
 **/
@RestController
@Api(value = "商户平台接口", tags = "商户平台接口", description = "商户平台 接口")
public class MerchantController {

    @Reference
    MerchantService merchantService;

    @Autowired
    SmsService smsService;

    @Autowired
    FileService fileService;

    @Autowired(required = false)
    AppService appService;






    @ApiOperation(value = "根据id查询商户信息")
    @GetMapping("/mer/{id}")
    public MerchantDTO querymerchantbyid(@PathVariable("id") Long id) {
        MerchantDTO merchantDTO =
                merchantService.querymerchantbyid(id);
        return merchantDTO;
    }


    @ApiOperation("获取验证码")
    @ApiImplicitParam(name = "phone", value = "手机号码", required = true, dataType = "String", paramType = "query")
    @GetMapping("/sms")
    public String getsmscode(String phone) {

        String key = smsService.sendMsg(phone);
        return key;
    }

    @ApiOperation("商户注册")
    @ApiImplicitParam(name = "MerchantRegisterVo", value = "注册商户信息", required = true,
            dataType = "MerchantRegisterVo", paramType = "body")
    @PostMapping("/merchants/register")
    public MerchantRegisterVo merchantRegisterVo(@RequestBody MerchantRegisterVo merchantRegisterVo) {


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

        //校验验证码
        smsService.checkverifycode(merchantRegisterVo.getVerifyKey(),merchantRegisterVo.getVerifyCode());
        //调用dubbo服务接口
//        MerchantDTO merchantDTO = new MerchantDTO();
        //向dto写入商户注册的信息
//        merchantDTO.setMobile(merchantRegisterVO.getMobile());
//        merchantDTO.setUsername(merchantRegisterVO.getUsername());
        //...
        //使用MapStruct转换对象
        MerchantDTO merchantDTO = MerchantRegisterConvert.INSTANCE.vo2dto(merchantRegisterVo);
        merchantService.createMerchant(merchantDTO);
        return merchantRegisterVo;
    }


    //上传证件照
    @ApiOperation("上传证照")
    @PostMapping("/upload")
    public String upload(@ApiParam(value = "证件照", required = true)
                         @RequestParam("file") MultipartFile multipartFile) {


        String originalFilename = multipartFile.getOriginalFilename();

        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") - 1);

        String fileName = UUID.randomUUID() + suffix;

        String fileUrl = null;
        try {
            fileUrl = fileService.upload(multipartFile.getBytes(), fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileUrl;
    }



    @ApiOperation("资质申请")
    @PostMapping("/my/merchants/save")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "merchantInfo", value = "商户认证资料", required = true, dataType = "MerchantDetailVO", paramType = "body")
    })
    public void saveMerchant(@RequestBody MerchantDetailVO merchantInfo){
//        Long merchantId = 1692365955852623874L;
//        MerchantDTO merchantDTO = MerchantDetailConvert.INSTANCE.vo2dto(merchantInfo);
//        merchantService.applyMerchant(merchantId,merchantDTO);


        Long merchantId = SecurityUtil.getMerchantId();
        MerchantDTO merchantDTO = MerchantDetailConvert.INSTANCE.vo2dto(merchantInfo);
        merchantService.applyMerchant(merchantId,merchantDTO);


    }

    @ApiOperation("查询商户下的应用列表")
    @GetMapping(value = "/my/apps")
    public List<AppDTO> queryMyApps() {
        //商户id
        Long merchantId = SecurityUtil.getMerchantId();
        return appService.queryAppByMerchant(merchantId);
    }







}