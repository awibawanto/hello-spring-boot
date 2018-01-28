package com.wibawanto.andika.service;

import com.wibawanto.andika.controller.exception.InsufficientCreditException;
import com.wibawanto.andika.controller.exception.InsufficientStockException;
import com.wibawanto.andika.model.Product;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author andika
 */
public interface ShoppingCartService {
    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    void checkout() throws InsufficientStockException, InsufficientCreditException;

    BigDecimal getTotal();
}
