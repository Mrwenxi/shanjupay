package com.shanjupay.merchant.service;

import com.shanjupay.merchant.api.MerchantService;
import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.common.domain.BusinessException;
import com.shanjupay.merchant.common.domain.CommonErrorCode;
import com.shanjupay.merchant.common.util.PhoneUtil;
import com.shanjupay.merchant.convert.MerchantConvert;
import com.shanjupay.merchant.entity.Merchant;
import com.shanjupay.merchant.mapper.MerchantMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: shanjupay
 * @ClassName MerchantServiceImpl
 * @description:
 * @author: wen
 * @create: 2023-08-16 08:44
 * @Version 1.0
 **/
@Service
public class MerchantServiceImpl implements MerchantService {

    @Reference
    MerchantMapper merchantMapper;


    @Override
    public MerchantDTO querymerchantbyid(Long id) {
        Merchant merchant = merchantMapper.selectById(id);
//        MerchantDTO merchantDTO = new MerchantDTO();
//        merchantDTO.setId(merchant.getId());
//        merchantDTO.setMerchantName(merchant.getMerchantName());
        //....
        return MerchantConvert.INSTANCE.entity2dto(merchant);
    }

    @Override
    public MerchantDTO createMerchant(MerchantDTO merchantDTO) {

/*        Merchant merchant = new Merchant();
        merchant.setUsername(merchantDTO.getUsername());
        merchant.setMobile(merchant.getMobile());
        merchant.setAuditStatus("0");*/

        //校验参数的合法性
/*        if(merchantDTO == null){
            throw new BusinessException(CommonErrorCode.E_100108);
        }
        if(StringUtils.isBlank(merchantDTO.getMobile())){
            throw new BusinessException(CommonErrorCode.E_100112);
        }
                //手机号格式校验
        if(!PhoneUtil.isMatches(merchantDTO.getMobile())){
            throw new BusinessException(CommonErrorCode.E_100109);
        }

        if(StringUtils.isBlank(merchantDTO.getUsername())){
            throw new BusinessException(CommonErrorCode.E_100111);
        }*/


        if(StringUtils.isBlank(merchantDTO.getPassword())){
            throw new BusinessException(merchantDTO.getPassword());
        }






        Merchant merchant = MerchantConvert.INSTANCE.dto2entity(merchantDTO);

        merchant.setAuditStatus("0");

        merchantMapper.insert(merchant);
        MerchantDTO merchantDTNew = MerchantConvert.INSTANCE.entity2dto(merchant);


        return merchantDTNew;
    }

    @Override
    public void applyMerchant(Long merchantId, MerchantDTO merchantDTO) {
        if(merchantId == null || merchantDTO == null){
            throw new BusinessException(CommonErrorCode.E_300009);
        }
        Merchant merchant = merchantMapper.selectById(merchantId);
        if(merchant == null){
            throw new BusinessException(CommonErrorCode.E_200002);
        }
        Merchant entity = MerchantConvert.INSTANCE.dto2entity(merchantDTO);
        entity.setId(merchant.getId());
        entity.setMobile(merchant.getMobile());
        entity.setAuditStatus("1");
        entity.setTenantId(merchant.getTenantId());
        merchantMapper.updateById(entity);
    }
}