package com.shanjupay.transaction.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shanjupay.merchant.common.domain.BusinessException;
import com.shanjupay.merchant.common.domain.CommonErrorCode;
import com.shanjupay.merchant.common.util.StringUtil;
import com.shanjupay.transaction.api.PayChannelService;
import com.shanjupay.transaction.api.dto.PayChannelDTO;
import com.shanjupay.transaction.api.dto.PayChannelParamDTO;
import com.shanjupay.transaction.api.dto.PlatformChannelDTO;
import com.shanjupay.transaction.convert.PayChannelParamConvert;
import com.shanjupay.transaction.convert.PlatformChannelConvert;
import com.shanjupay.transaction.entity.AppPlatformChannel;
import com.shanjupay.transaction.entity.PayChannelParam;
import com.shanjupay.transaction.entity.PlatformChannel;
import com.shanjupay.transaction.mapper.AppPlatformChannelMapper;
import com.shanjupay.transaction.mapper.PayChannelParamMapper;
import com.shanjupay.transaction.mapper.PlatformChannelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: shanjupay
 * @ClassName PayChannelServiceImpl
 * @description:
 * @author: wen
 * @create: 2023-08-22 10:53
 * @Version 1.0
 **/
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

/**
 * 支付渠道服务实现类
 */
@Service // Dubbo服务注解
public class PayChannelServiceImpl implements PayChannelService {

    @Autowired
    PlatformChannelMapper platformChannelMapper;

    @Autowired
    AppPlatformChannelMapper appPlatformChannelMapper;

    @Autowired
    PayChannelParamMapper payChannelParamMapper;

    @Override
    public List<PlatformChannelDTO> queryPlatformChannel() throws BusinessException {
        // 查询platform_channel表的全部记录
        List<PlatformChannel> platformChannels = platformChannelMapper.selectList(null);
        // 将platformChannels转成包含dto的list
        return PlatformChannelConvert.INSTANCE.listentity2listdto(platformChannels);
    }

    @Override
    @Transactional
    public void bindPlatformChannelForApp(String appId, String platformChannelCodes) throws BusinessException {
        // 根据应用id和服务类型code查询，如果已经绑定则不再插入，否则插入记录
        AppPlatformChannel appPlatformChannel = appPlatformChannelMapper.selectOne(
                new LambdaQueryWrapper<AppPlatformChannel>()
                        .eq(AppPlatformChannel::getAppId, appId)
                        .eq(AppPlatformChannel::getPlatformChannel, platformChannelCodes)
        );
        if (appPlatformChannel == null) {
            // 向app_platform_channel插入
            AppPlatformChannel entity = new AppPlatformChannel();
            entity.setAppId(appId); // 应用id
            entity.setPlatformChannel(platformChannelCodes); // 服务类型code
            appPlatformChannelMapper.insert(entity);
        }
    }

    @Override
    public int queryAppBindPlatformChannel(String appId, String platformChannel) throws BusinessException {
        AppPlatformChannel appPlatformChannel = appPlatformChannelMapper.selectOne(
                new LambdaQueryWrapper<AppPlatformChannel>()
                        .eq(AppPlatformChannel::getAppId, appId)
                        .eq(AppPlatformChannel::getPlatformChannel, platformChannel)
        );
        if (appPlatformChannel != null) {
            return 1;
        }
        return 0;
    }

    @Override
    public List<PayChannelDTO> queryPayChannelByPlatformChannel(String platformChannelCode) throws BusinessException {
        return platformChannelMapper.selectPayChannelByPlatformChannel(platformChannelCode);
    }

    @Override
    public void savePayChannelParam(PayChannelParamDTO payChannelParamDTO) throws BusinessException {
        // 保存支付渠道参数的逻辑
        if(payChannelParamDTO == null
                || payChannelParamDTO.getChannelName() == null
                ||StringUtil.isBlank(payChannelParamDTO.getAppId())
                || payChannelParamDTO.getParam()== null){
            throw new BusinessException(CommonErrorCode.E_300009);
        }


        Long appPlatformChannelId = selectIdByAppPlatformChannel(payChannelParamDTO.getAppId(),
                payChannelParamDTO.getPlatformChannelCode());


        if(appPlatformChannelId != null){

            throw new BusinessException(CommonErrorCode.E_300010);
        }


        //根据应用与服务类型的绑定id和支付渠道查询PayChannelParam的一条记录
        PayChannelParam payChannelParam = payChannelParamMapper.selectOne(new LambdaQueryWrapper<PayChannelParam>()
                .eq(PayChannelParam::getPayChannel, payChannelParamDTO.getPayChannel())
                .eq(PayChannelParam::getAppPlatformChannelId, appPlatformChannelId)
        );

        //如果存在配置则更新
        if(payChannelParam != null){
            payChannelParam.setChannelName(payChannelParam.getChannelName());//配置名称
            payChannelParam.setParam(payChannelParam.getParam());//json格式的参数
            payChannelParamMapper.updateById(payChannelParam);
        }else{
            //否则添加配置
            PayChannelParam entity = PayChannelParamConvert.INSTANCE.dto2entity(payChannelParamDTO);
            entity.setId(null);
            entity.setAppPlatformChannelId(appPlatformChannelId);//应用与服务类型绑定关系id
            payChannelParamMapper.insert(entity);
        }





    }

    private Long selectIdByAppPlatformChannel(String appId,String platformChannelCode){
        AppPlatformChannel appPlatformChannel = appPlatformChannelMapper.selectOne(new LambdaQueryWrapper<AppPlatformChannel>().eq(AppPlatformChannel::getAppId, appId)
                .eq(AppPlatformChannel::getPlatformChannel, platformChannelCode));
        if(appPlatformChannel!=null){
            return appPlatformChannel.getId();//应用与服务类型的绑定id
        }
        return null;
    }




}
