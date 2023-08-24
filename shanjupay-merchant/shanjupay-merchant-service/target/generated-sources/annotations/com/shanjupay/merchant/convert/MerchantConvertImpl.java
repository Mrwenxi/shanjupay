package com.shanjupay.merchant.convert;

import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.entity.Merchant;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-23T17:01:29+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_111 (Oracle Corporation)"
)
public class MerchantConvertImpl implements MerchantConvert {

    @Override
    public MerchantDTO entity2dto(Merchant merchant) {
        if ( merchant == null ) {
            return null;
        }

        MerchantDTO merchantDTO = new MerchantDTO();

        merchantDTO.setId( merchant.getId() );
        merchantDTO.setMerchantName( merchant.getMerchantName() );
        merchantDTO.setMerchantNo( merchant.getMerchantNo() );
        merchantDTO.setMerchantAddress( merchant.getMerchantAddress() );
        merchantDTO.setMerchantType( merchant.getMerchantType() );
        merchantDTO.setBusinessLicensesImg( merchant.getBusinessLicensesImg() );
        merchantDTO.setIdCardFrontImg( merchant.getIdCardFrontImg() );
        merchantDTO.setIdCardAfterImg( merchant.getIdCardAfterImg() );
        merchantDTO.setUsername( merchant.getUsername() );
        merchantDTO.setMobile( merchant.getMobile() );
        merchantDTO.setContactsAddress( merchant.getContactsAddress() );
        merchantDTO.setAuditStatus( merchant.getAuditStatus() );
        merchantDTO.setTenantId( merchant.getTenantId() );

        return merchantDTO;
    }

    @Override
    public Merchant dto2entity(MerchantDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Merchant merchant = new Merchant();

        merchant.setId( dto.getId() );
        merchant.setMerchantName( dto.getMerchantName() );
        merchant.setMerchantNo( dto.getMerchantNo() );
        merchant.setMerchantAddress( dto.getMerchantAddress() );
        merchant.setMerchantType( dto.getMerchantType() );
        merchant.setBusinessLicensesImg( dto.getBusinessLicensesImg() );
        merchant.setIdCardFrontImg( dto.getIdCardFrontImg() );
        merchant.setIdCardAfterImg( dto.getIdCardAfterImg() );
        merchant.setUsername( dto.getUsername() );
        merchant.setMobile( dto.getMobile() );
        merchant.setContactsAddress( dto.getContactsAddress() );
        merchant.setAuditStatus( dto.getAuditStatus() );
        merchant.setTenantId( dto.getTenantId() );

        return merchant;
    }
}
