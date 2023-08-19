package com.shanjupay.merchant.convert;

import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.vo.MerchantRegisterVo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-19T10:37:05+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_111 (Oracle Corporation)"
)
public class MerchantRegisterConvertImpl implements MerchantRegisterConvert {

    @Override
    public MerchantDTO vo2dto(MerchantRegisterVo vo) {
        if ( vo == null ) {
            return null;
        }

        MerchantDTO merchantDTO = new MerchantDTO();

        merchantDTO.setUsername( vo.getUsername() );
        merchantDTO.setPassword( vo.getPassword() );
        merchantDTO.setMobile( vo.getMobile() );

        return merchantDTO;
    }

    @Override
    public MerchantRegisterVo dto2vo(MerchantDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MerchantRegisterVo merchantRegisterVo = new MerchantRegisterVo();

        merchantRegisterVo.setMobile( dto.getMobile() );
        merchantRegisterVo.setUsername( dto.getUsername() );
        merchantRegisterVo.setPassword( dto.getPassword() );

        return merchantRegisterVo;
    }
}
