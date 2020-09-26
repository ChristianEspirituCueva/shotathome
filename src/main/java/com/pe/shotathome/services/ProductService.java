package com.pe.shotathome.services;

import com.pe.shotathome.entity.Product;
import com.pe.shotathome.entity.Store;
import com.pe.shotathome.exeptions.GeneralServiceException;
import com.pe.shotathome.exeptions.NoDataFoundException;
import com.pe.shotathome.exeptions.ValidateServiceException;
import com.pe.shotathome.repository.ProductRepository;
import com.pe.shotathome.repository.StoreRepository;
import com.pe.shotathome.validators.ProductValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;


    public List<Product> findAll(Pageable page){
        try{
            return productRepository.findAll(page).toList();
        }catch (ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage(),e);
            throw e;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new GeneralServiceException(e.getMessage(),e);
        }
    }

    public Product findById(Long id) {
        try {
            return productRepository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("La producto no existe"));
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    public void delete(Long productId) {
        try {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new NoDataFoundException("No existe el producto"));
            productRepository.delete(product);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }


    @Transactional
    public Product save(Product product) {
        try {
            ProductValidator.save(product);

            if(product.getId() == null) {
                Product newProduct = productRepository.save(product);
                return newProduct;
            }

            Product exitProduct = productRepository.findById(product.getId())
                    .orElseThrow(() -> new NoDataFoundException("No existe el producto"));

            //objeto store pa
            Store exitStore = storeRepository.findById(product.getStore().getId())//se manda el id de la tienda
                    .orElseThrow(() -> new NoDataFoundException("No existe la tienda"));

            exitProduct.setName(product.getName());
            exitProduct.setPrice(product.getPrice());
            exitProduct.setStore(exitStore);
            productRepository.save(exitProduct);

            return exitProduct;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }

}
