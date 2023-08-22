package com.shanjupay.merchant.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanjupay.merchant.api.AppService;
import com.shanjupay.merchant.api.dto.AppDTO;
import com.shanjupay.merchant.common.domain.BusinessException;
import com.shanjupay.merchant.common.domain.CommonErrorCode;
import com.shanjupay.merchant.common.util.RandomUuidUtil;
import com.shanjupay.merchant.convert.AppCovert;
import com.shanjupay.merchant.entity.App;
import com.shanjupay.merchant.entity.Merchant;
import com.shanjupay.merchant.mapper.AppMapper;
import com.shanjupay.merchant.mapper.MerchantMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

/**
 * @program: shanjupay
 * @ClassName AppServiceImpl
 * @description:
 * @author: wen
 * @create: 2023-08-19 14:34
 * @Version 1.0
 **/
@Service
public class AppServiceImpl implements AppService {

    @Autowired
    MerchantMapper merchantMapper;

    @Autowired
    AppMapper appMapper;

    @Override
    public AppDTO createApp(Long merchantId, AppDTO appDTO) throws BusinessException {
        Merchant merchant = merchantMapper.selectById(merchantId);
        if(merchant == null){
            throw  new BusinessException(CommonErrorCode.E_200002);
        }
        if(!"2".equals(merchant.getAuditStatus())){
            throw  new BusinessException(CommonErrorCode.E_200003);
        }
        if(isExistAppName((appDTO.getAppName()))){
            throw new BusinessException(CommonErrorCode.E_200004);
        }


        appDTO.setAppId(RandomUuidUtil.getUUID());
        appDTO.setMerchantId(merchantId);

        App app = AppCovert.INSTANCE.dto2entity(appDTO);

        appMapper.insert(app);

        return AppCovert.INSTANCE.entity2dto(app);
    }

    @Override
    public List<AppDTO> queryAppByMerchant(Long merchantId) throws BusinessException {
        List<App> apps =
                appMapper.selectList(new LambdaQueryWrapper<App>()
                        .eq(App::getMerchantId, merchantId));
        return AppCovert.INSTANCE.listentity2dto(apps);
    }

    @Override
    public AppDTO getAppById(String id) throws BusinessException {
        QueryWrapper<App> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("app_id",id);
        App app = appMapper.selectOne(queryWrapper);
        return AppCovert.INSTANCE.entity2dto(app);
    }


    private Boolean isExistAppName(String appName){
        Integer count = appMapper.selectCount(new LambdaQueryWrapper<App>().eq(App::getAppName, appName));
        QueryWrapper<Object> objectQueryWrapper = new QueryWrapper<>();
        return count >0;
    }


}