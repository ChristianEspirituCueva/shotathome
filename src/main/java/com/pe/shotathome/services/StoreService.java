package com.pe.shotathome.services;

import com.pe.shotathome.entity.Order;
import com.pe.shotathome.entity.OrderLines;
import com.pe.shotathome.entity.Product;
import com.pe.shotathome.entity.Store;
import com.pe.shotathome.exeptions.GeneralServiceException;
import com.pe.shotathome.exeptions.NoDataFoundException;
import com.pe.shotathome.exeptions.ValidateServiceException;
import com.pe.shotathome.repository.ProductRepository;
import com.pe.shotathome.repository.StoreRepository;
import com.pe.shotathome.validators.OrderValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

//develop
@Slf4j
@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Store> findAll(Pageable page){
        try {
            return storeRepository.findAll(page).toList();
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    public Store findById(Long id) {
        try {
            return storeRepository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("La orden no existe"));
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    public void delete(Long id) {
        try {
            Store store = storeRepository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("La orden no existe"));

            storeRepository.delete(store);

        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    //FALTA LA PARTE DE TRANSACCIONAL


}
