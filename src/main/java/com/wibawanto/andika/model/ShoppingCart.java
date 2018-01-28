package com.wibawanto.andika.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author andika
 */
public class ShoppingCart {

    @JsonSerialize(keyUsing = ProductSerializer.class)
    private Map<Product, Integer> products;
    private BigDecimal total;
    private String errorMessage;

    public ShoppingCart(Map<Product, Integer> products, BigDecimal total) {
        this.products = products;
        this.total = total;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    private String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
