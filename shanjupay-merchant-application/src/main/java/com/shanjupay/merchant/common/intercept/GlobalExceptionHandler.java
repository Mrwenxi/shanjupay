package com.shanjupay.merchant.common.intercept;

import com.shanjupay.merchant.common.domain.BusinessException;
import com.shanjupay.merchant.common.domain.CommonErrorCode;
import com.shanjupay.merchant.common.domain.ErrorCode;
import com.shanjupay.merchant.common.domain.RestErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: shanjupay
 * @ClassName GlobalExceptionHandler
 * @description:
 * @author: wen
 * @create: 2023-08-18 11:08
 * @Version 1.0
 **/
public class GlobalExceptionHandler {



    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse processExcetion( HttpServletRequest request,
                                              HttpServletResponse response,
                                              Exception e){

        if(e instanceof BusinessException){
            BusinessException businessException= (BusinessException) e;
            ErrorCode errorCode = businessException.getErrorCode();
            int code = errorCode.getCode();
            String desc = errorCode.getDesc();
            return new RestErrorResponse(String.valueOf(code),desc);
        }


        //99999系统未知错误
        return new RestErrorResponse(String.valueOf(CommonErrorCode.UNKNOWN.getCode()+""),CommonErrorCode.UNKNOWN.getDesc());

    }

}