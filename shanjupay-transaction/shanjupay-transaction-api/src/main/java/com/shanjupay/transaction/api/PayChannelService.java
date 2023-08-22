package com.shanjupay.transaction.api;

import com.shanjupay.merchant.common.domain.BusinessException;
import com.shanjupay.transaction.api.dto.PlatformChannelDTO;

import java.util.List;

/**
 * @program: shanjupay
 * @ClassName PayChannelService
 * @description:
 * @author: wen
 * @create: 2023-08-22 10:52
 * @Version 1.0
 **/
public interface PayChannelService {

    List<PlatformChannelDTO> queryPlatformChannel() throws BusinessException;


    void bindPlatformChannelForApp(String appId,String platformChannelCodes) throws BusinessException;


    int queryAppBindPlatformChannel(String appId,String platformChannel)throws BusinessException;



}