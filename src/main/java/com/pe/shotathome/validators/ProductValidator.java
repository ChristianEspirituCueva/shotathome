package com.pe.shotathome.validators;

import com.pe.shotathome.entity.Product;
import com.pe.shotathome.exeptions.ValidateServiceException;

public class ProductValidator {
    public static void save(Product product) {

        if(product.getName() == null || product.getName().trim().isEmpty()) {
            throw new ValidateServiceException("El nombre es requerido");
        }

        if(product.getName().length() > 50) {
            throw new ValidateServiceException("El nombre es muy largo (max 100)");
        }

        if(product.getPrice() == null) {
            throw new ValidateServiceException("El precio es requerido");
        }

        if(product.getPrice() < 0) {
            throw new ValidateServiceException("El precio es incorrecto");
        }
    }
}
