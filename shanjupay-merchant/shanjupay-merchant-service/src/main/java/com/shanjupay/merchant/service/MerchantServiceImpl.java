package com.shanjupay.merchant.service;

import com.shanjupay.merchant.api.MerchantService;
import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.entity.Merchant;
import com.shanjupay.merchant.mapper.MerchantMapper;
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
@org.apache.dubbo.config.annotation.Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    MerchantMapper merchantMapper;


    @Override
    public MerchantDTO querymerchantbyid(Long id) {
        Merchant merchant = merchantMapper.selectById(id);
        MerchantDTO merchantDTO = new MerchantDTO();
        merchantDTO.setId(merchant.getId());
        merchantDTO.setMerchantName(merchant.getMerchantName());
        merchantDTO.setMerchantNo(merchant.getMerchantNo());
        merchantDTO.setMerchantAddress(merchant.getMerchantAddress());
        merchantDTO.setMerchantType(merchant.getMerchantType());
        merchantDTO.setBusinessLicensesImg(merchant.getBusinessLicensesImg());
        merchantDTO.setIdCardFrontImg(merchant.getIdCardFrontImg());
        merchantDTO.setIdCardAfterImg(merchant.getIdCardAfterImg());
        merchantDTO.setUsername(merchant.getUsername());
        merchantDTO.setMobile(merchant.getMobile());
        merchantDTO.setContactsAddress(merchant.getContactsAddress());
        merchantDTO.setAuditStatus(merchant.getAuditStatus());
        merchantDTO.setTenantId(merchant.getTenantId());

        return merchantDTO ;
    }
}