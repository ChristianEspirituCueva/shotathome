package com.pe.shotathome.controller;



import com.pe.shotathome.entity.Store;
import com.pe.shotathome.exeptions.ResourceNotFoundException;
import com.pe.shotathome.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/stores")
    public Page<Store> getStores(Pageable pageable) {
        return storeRepository.findAll(pageable);
    }


    @PostMapping("/stores")
    public Store createStore(@Valid @RequestBody Store store) {
        return storeRepository.save(store);
    }

    @PutMapping("/stores/{storeId}")
    public Store updateStore(@PathVariable Long storeId,
                                   @Valid @RequestBody Store storeRequest) {
        return storeRepository.findById(storeId)
                .map(store -> {
                    store.setName(storeRequest.getName());
                    store.setCountry(storeRequest.getCountry());
                    return storeRepository.save(store);
                }).orElseThrow(() -> new ResourceNotFoundException("Store not found with id " + storeId));
    }


    @DeleteMapping("/stores/{storeId}")
    public ResponseEntity<?> deleteStore(@PathVariable Long storeId) {
        return storeRepository.findById(storeId)
                .map(store -> {
                    storeRepository.delete(store);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Store not found with id " + storeId));
    }






}
