package com.wibawanto.andika.service;

import com.wibawanto.andika.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author andika
 */
public interface ProductService {

    Product findById(Long id);

    Page<Product> findAll(Pageable pageable);

}
