package com.pe.shotathome.converters;

import com.pe.shotathome.dto.StoreDto;
import com.pe.shotathome.entity.Store;

public class StoreConverter {
    public static Store DtoToEntity(StoreDto str) {
        Store store=new Store();
        store.setName(str.getName());
        store.setRUC(str.getRUC());
        store.setPhone(str.getPhone());
        store.setCountry(str.getCountry());
        store.setCity(str.getCity());
        store.setAddress(str.getAddress());
        return store;
    }
    public static StoreDto EntityToDto(Store str) {
        StoreDto storeDto=new StoreDto();
        storeDto.setName(str.getName());
        storeDto.setRUC(str.getRUC());
        storeDto.setPhone(str.getPhone());
        storeDto.setCountry(str.getCountry());
        storeDto.setCity(str.getCity());
        storeDto.setAddress(str.getAddress());
        return storeDto;
    }
}
