package com.pe.shotathome.converters;

import com.pe.shotathome.dto.ProductDto;
import com.pe.shotathome.entity.Product;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductConverter {
    public static Product DtoToEntity(ProductDto prd) {
        Product product=new Product();
        product.setName(prd.getName());
        product.setPrice(prd.getPrice());
        product.setDetail(prd.getDetail());
        return product;
    }
    public static ProductDto EntityToDto(Product prd) {
        ProductDto productDTO=new ProductDto();
        productDTO.setName(prd.getName());
        productDTO.setPrice(prd.getPrice());
        productDTO.setDetail(prd.getDetail());
        return productDTO;
    }
}
