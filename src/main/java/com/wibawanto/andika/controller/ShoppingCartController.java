package com.wibawanto.andika.controller;

import com.wibawanto.andika.controller.exception.InsufficientCreditException;
import com.wibawanto.andika.controller.exception.InsufficientStockException;
import com.wibawanto.andika.model.ShoppingCart;
import com.wibawanto.andika.service.ProductService;
import com.wibawanto.andika.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author andika
 */
@RestController
@RequestMapping(value = "/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ShoppingCart shoppingCart() {
        return new ShoppingCart(shoppingCartService.getProductsInCart(), shoppingCartService.getTotal());
    }

    @GetMapping("/addProduct/{productId}")
    public ShoppingCart addProductToCart(@PathVariable("productId") Long productId) {
        shoppingCartService.addProduct(productService.findById(productId));
        return shoppingCart();
    }

    @GetMapping("/removeProduct/{productId}")
    public ShoppingCart removeProductFromCart(@PathVariable("productId") Long productId) {
        shoppingCartService.removeProduct(productService.findById(productId));
        return shoppingCart();
    }

    @GetMapping("/checkout")
    public ShoppingCart checkout() throws InsufficientStockException, InsufficientCreditException {
        shoppingCartService.checkout();
        return shoppingCart();
    }

    @ExceptionHandler(InsufficientStockException.class)
    public @ResponseBody String handleInsufficientStockException(InsufficientStockException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InsufficientCreditException.class)
    public @ResponseBody String handleInsufficientCreditException(InsufficientCreditException ex) {
        return ex.getMessage();
    }
}

