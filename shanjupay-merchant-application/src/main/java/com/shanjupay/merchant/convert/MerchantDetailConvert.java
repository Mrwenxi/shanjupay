package com.shanjupay.merchant.convert;

import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.vo.MerchantDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @program: shanjupay
 * @ClassName MerchantDetailConvert
 * @description:
 * @author: wen
 * @create: 2023-08-19 11:01
 * @Version 1.0
 **/
@Mapper
public interface MerchantDetailConvert {

    MerchantDetailConvert INSTANCE = Mappers.getMapper(MerchantDetailConvert.class);

    MerchantDTO vo2dto (MerchantDetailVO vo);

    MerchantDetailVO dto2vo(MerchantDTO dto);


}