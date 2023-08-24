package com.shanjupay.merchant.controller;

import com.shanjupay.merchant.common.domain.BusinessException;
import com.shanjupay.merchant.common.domain.CommonErrorCode;
import com.shanjupay.merchant.common.util.SecurityUtil;
import com.shanjupay.transaction.api.PayChannelService;
import com.shanjupay.transaction.api.dto.PayChannelDTO;
import com.shanjupay.transaction.api.dto.PayChannelParamDTO;
import com.shanjupay.transaction.api.dto.PlatformChannelDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: shanjupay
 * @ClassName PlatformParamController
 * @description:
 * @author: wen
 * @create: 2023-08-22 10:56
 * @Version 1.0
 **/
@Api(value = "商户平台-渠道和支付参数相关", tags = "商户平台-渠道和支付参数", description = "商户平台-渠道和支付参数相关")
@RestController
@Slf4j
public class PlatformParamController {

    @Reference
    PayChannelService payChannelService;

    @ApiOperation("获取平台服务类型")
    @GetMapping(value="/my/platform-channels")
    public List<PlatformChannelDTO> queryPlatformChannel(){
        return payChannelService.queryPlatformChannel();
    }


    @ApiOperation("根据服务类型查询支付渠道")
     @ApiImplicitParam(
             name = "platformChannelCode", value = "服务类型代码",
            required = true, dataType = "String", paramType = "path")
    @GetMapping(value="/my/pay-channels/platform-channel/{platformChannelCode}")
    List<PayChannelDTO> queryPayChannelByPlatformChannel(@PathVariable("platformChannelCode") String platformChannelCode){
        return payChannelService.queryPayChannelByPlatformChannel(platformChannelCode);

    }

    @ApiOperation("商户配置支付渠道参数")
    @ApiImplicitParam(name = "payChannelParamDTO", value = "支付渠道参数",
            required = true, dataType = "PayChannelParamDTO",
            paramType = "body")
    @RequestMapping(value = "/my/pay-channel-params",
            method = {RequestMethod.POST,RequestMethod.PUT})
    public void createPayChannelParam(@RequestBody PayChannelParamDTO payChannelParamDTO){
//        if(payChannelParamDTO == null || payChannelParamDTO.getChannelName() == null){
//            throw new BusinessException(CommonErrorCode.E_300009);
//        }
        //商户id
        Long merchantId = SecurityUtil.getMerchantId();
        payChannelParamDTO.setMerchantId(merchantId);
        payChannelService.savePayChannelParam(payChannelParamDTO);

    }








}