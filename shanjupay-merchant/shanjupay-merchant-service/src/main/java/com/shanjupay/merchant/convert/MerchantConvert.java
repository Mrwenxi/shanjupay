package com.shanjupay.merchant.convert;

import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.entity.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/** @Author wen
 * @Description //TODO 
 * @Date 10:47 2023/8/18
 * @Param 
 * @return 
 **/
@Mapper
public interface MerchantConvert {

    MerchantConvert INSTANCE =Mappers.getMapper(MerchantConvert.class);

    MerchantDTO entity2dto(Merchant entity);

    Merchant dto2entity(MerchantDTO dto);
}
