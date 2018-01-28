package com.wibawanto.andika.controller.exception;

import com.wibawanto.andika.model.Product;

/**
 * @author andika
 */
public class InsufficientStockException extends Exception {
    private Product product;
    private static final String DEFAULT_MESSAGE = "Not enough products in stock";

    public InsufficientStockException() {
        super(DEFAULT_MESSAGE);
    }

    public InsufficientStockException(Product product) {
        super("Not enough " + product.getName() + " products in stock. Only " + product.getQuantity() + " left");

        this.product = product;
    }
}
