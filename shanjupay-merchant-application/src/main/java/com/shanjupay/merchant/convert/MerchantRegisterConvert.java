package com.shanjupay.merchant.convert;

import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.vo.MerchantRegisterVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @program: shanjupay
 * @ClassName MerchantRegisterConvert
 * @description:
 * @author: wen
 * @create: 2023-08-18 10:55
 * @Version 1.0
 **/
@Mapper
public interface MerchantRegisterConvert {

    MerchantRegisterConvert INSTANCE = Mappers.getMapper(MerchantRegisterConvert.class);

    MerchantDTO vo2dto (MerchantRegisterVo vo);

    MerchantRegisterVo dto2vo (MerchantDTO dto);
}