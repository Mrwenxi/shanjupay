package com.shanjupay.merchant.common.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @program: shanjupay
 * @ClassName RestErrorResponse
 * @description:
 * @author: wen
 * @create: 2023-08-18 11:10
 * @Version 1.0
 **/
@ApiModel(value = "RestErrorResponse", description = "错误响应参数包装")
@Data
public class RestErrorResponse {

    private String errCode;

    private String errMessage;

    public RestErrorResponse(String errCode,String errMessage){
        this.errCode = errCode;
        this.errMessage= errMessage;
    }
}