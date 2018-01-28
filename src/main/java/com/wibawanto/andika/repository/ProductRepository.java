package com.wibawanto.andika.repository;

import com.wibawanto.andika.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author andika
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
