package com.pe.shotathome.controller;



import com.pe.shotathome.entity.Product;
import com.pe.shotathome.exeptions.ResourceNotFoundException;
import com.pe.shotathome.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


    @PostMapping("/products")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/products/{productId}")
    public Product updateProduct(@PathVariable Long productId,
                                   @Valid @RequestBody Product productRequest) {
        return productRepository.findById(productId)
                .map(product -> {
                    product.setName(productRequest.getName());
                    product.setPrice(productRequest.getPrice());
                    return productRepository.save(product);
                }).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));
    }


    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        return productRepository.findById(productId)
                .map(product -> {
                    productRepository.delete(product);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));
    }






}
