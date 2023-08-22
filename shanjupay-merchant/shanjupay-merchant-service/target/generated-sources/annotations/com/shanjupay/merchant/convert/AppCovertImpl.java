package com.shanjupay.merchant.convert;

import com.shanjupay.merchant.api.dto.AppDTO;
import com.shanjupay.merchant.entity.App;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-21T09:38:17+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_111 (Oracle Corporation)"
)
public class AppCovertImpl implements AppCovert {

    @Override
    public AppDTO entity2dto(App entity) {
        if ( entity == null ) {
            return null;
        }

        AppDTO appDTO = new AppDTO();

        appDTO.setId( entity.getId() );
        appDTO.setAppId( entity.getAppId() );
        appDTO.setAppName( entity.getAppName() );
        appDTO.setMerchantId( entity.getMerchantId() );
        appDTO.setPublicKey( entity.getPublicKey() );
        appDTO.setNotifyUrl( entity.getNotifyUrl() );

        return appDTO;
    }

    @Override
    public App dto2entity(AppDTO dto) {
        if ( dto == null ) {
            return null;
        }

        App app = new App();

        app.setId( dto.getId() );
        app.setAppId( dto.getAppId() );
        app.setAppName( dto.getAppName() );
        app.setMerchantId( dto.getMerchantId() );
        app.setPublicKey( dto.getPublicKey() );
        app.setNotifyUrl( dto.getNotifyUrl() );

        return app;
    }

    @Override
    public List<AppDTO> listentity2dto(List<App> list) {
        if ( list == null ) {
            return null;
        }

        List<AppDTO> list1 = new ArrayList<AppDTO>( list.size() );
        for ( App app : list ) {
            list1.add( entity2dto( app ) );
        }

        return list1;
    }
}
