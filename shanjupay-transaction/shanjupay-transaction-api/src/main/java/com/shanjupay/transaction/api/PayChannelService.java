package com.shanjupay.transaction.api;

import com.shanjupay.merchant.common.domain.BusinessException;
import com.shanjupay.transaction.api.dto.PayChannelDTO;
import com.shanjupay.transaction.api.dto.PayChannelParamDTO;
import com.shanjupay.transaction.api.dto.PlatformChannelDTO;

import java.util.List;

/**
 * @program: shanjupay
 * @ClassName PayChannelService
 * @description:
 * @author: wen
 * @create: 2023-08-22 10:52
 * @Version 1.0
/**
 * 支付渠道服务接口
 */
public interface PayChannelService {

    /**
     * 查询平台渠道列表
     *
     * @return 平台渠道DTO列表
     * @throws BusinessException 业务异常
     */
    List<PlatformChannelDTO> queryPlatformChannel() throws BusinessException;

    /**
     * 为应用绑定平台渠道
     *
     * @param appId               应用ID
     * @param platformChannelCodes 平台渠道代码
     * @throws BusinessException 业务异常
     */
    void bindPlatformChannelForApp(String appId, String platformChannelCodes) throws BusinessException;

    /**
     * 查询应用绑定的平台渠道
     *
     * @param appId            应用ID
     * @param platformChannel 平台渠道代码
     * @return 绑定关系数量
     * @throws BusinessException 业务异常
     */
    int queryAppBindPlatformChannel(String appId, String platformChannel) throws BusinessException;

    /**
     * 根据平台渠道代码查询支付渠道列表
     *
     * @param platformChannelCode 平台渠道代码
     * @return 支付渠道DTO列表
     * @throws BusinessException 业务异常
     */
    List<PayChannelDTO> queryPayChannelByPlatformChannel(String platformChannelCode) throws BusinessException;

    /**
     * 保存支付渠道参数
     *
     * @param payChannelParam 支付渠道参数DTO
     * @throws BusinessException 业务异常
     */
    void savePayChannelParam(PayChannelParamDTO payChannelParam) throws BusinessException;
}
