package com.pe.shotathome.validators;

import com.pe.shotathome.entity.Store;
import com.pe.shotathome.exeptions.ValidateServiceException;

public class StoreValidator {

    public static void save(Store store) {

        if(store.getName() == null || store.getName().trim().isEmpty()) {
            throw new ValidateServiceException("El nombre es requerido");
        }

        if(store.getName().length() > 100) {
            throw new ValidateServiceException("El nombre es muy largo (max 100)");
        }

        if(store.getAddress() == null) {
            throw new ValidateServiceException("La dirección es requerida");
        }

        if(store.getCity() == null) {
            throw new ValidateServiceException("La dirección es requerida");
        }

        if(store.getCountry() == null) {
            throw new ValidateServiceException("El pais es requerido");
        }

        if(store.getPhone() == null) {
            throw new ValidateServiceException("El RUC es requerido");
        }

        if(store.getRUC() == null) {
            throw new ValidateServiceException("La dirección es requerida");
        }
    }

}
