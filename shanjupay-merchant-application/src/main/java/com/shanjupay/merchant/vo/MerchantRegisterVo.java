package com.shanjupay.merchant.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @program: shanjupay
 * @ClassName MerchantRegisterVo
 * @description:
 * @author: wen
 * @create: 2023-08-17 19:36
 * @Version 1.0
 **/
@ApiModel(value = "MerchantRegisterVo", description = "商户注册对象")
@Data
public class MerchantRegisterVo {

    @ApiModelProperty("商户手机号")
    private String mobile;

    @ApiModelProperty("商户用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码")
    private String verifyCode;

    @ApiModelProperty("验证码key")
    private String verifyKey;

}