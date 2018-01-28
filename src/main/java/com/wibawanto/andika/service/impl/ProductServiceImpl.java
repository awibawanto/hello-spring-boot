package com.wibawanto.andika.service.impl;

import com.wibawanto.andika.model.Product;
import com.wibawanto.andika.repository.ProductRepository;
import com.wibawanto.andika.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author andika
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
