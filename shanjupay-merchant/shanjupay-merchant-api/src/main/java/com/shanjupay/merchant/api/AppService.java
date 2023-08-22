package com.shanjupay.merchant.api;

import com.shanjupay.merchant.api.dto.AppDTO;
import com.shanjupay.merchant.common.domain.BusinessException;

import java.util.List;

/**
 * @program: shanjupay
 * @ClassName AppService
 * @description:
 * @author: wen
 * @create: 2023-08-19 14:33
 * @Version 1.0
 **/
public interface AppService {
/** @Author wen
 * @Description //TODO 
 * @Date 14:35 2023/8/19
 * @Param 
 * @return 
 **/
    AppDTO createApp(Long merchantId, AppDTO appDTO) throws BusinessException;

    List<AppDTO> queryAppByMerchant(Long merchantId) throws BusinessException;

    AppDTO getAppById(String id)throws BusinessException;


}