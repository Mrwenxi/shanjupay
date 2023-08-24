package com.shanjupay.merchant.api;

import com.shanjupay.merchant.api.dto.MerchantDTO;


public interface MerchantService {
    /** @Author wen
     * @Description //TODO
     * @Date 8:43 2023/8/16
     * @Param id
     * @return
     **/

   MerchantDTO querymerchantbyid(Long id);


    /** @Author wen
     * @Description //TODO
     * @Date 19:19 2023/8/17
     * @Param merchantDTO
     * @return
     **/
    MerchantDTO createMerchant(MerchantDTO merchantDTO);


    void applyMerchant(Long merchantId,MerchantDTO merchantDTO) ;


}
