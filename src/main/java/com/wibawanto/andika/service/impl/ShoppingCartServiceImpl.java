package com.wibawanto.andika.service.impl;

import com.wibawanto.andika.controller.exception.InsufficientCreditException;
import com.wibawanto.andika.controller.exception.InsufficientStockException;
import com.wibawanto.andika.model.Product;
import com.wibawanto.andika.model.User;
import com.wibawanto.andika.repository.ProductRepository;
import com.wibawanto.andika.repository.UserRepository;
import com.wibawanto.andika.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author andika
 * Store shopping cart in Session
 */
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private static Logger logger = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    private Map<Product, Integer> products = new HashMap<>();

    /**
     * If product is in the map just increment quantity by 1.
     * If product is not in the map with, add it with quantity 1
     *
     * @param product
     */
    @Override
    public void addProduct(Product product) {
        if (products.containsKey(product)) {
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }

    /**
     * If product is in the map with quantity > 1, just decrement quantity by 1.
     * If product is in the map with quantity 1, remove it from map
     *
     * @param product
     */
    @Override
    public void removeProduct(Product product) {
        if (products.containsKey(product)) {
            if (products.get(product) > 1)
                products.replace(product, products.get(product) - 1);
            else if (products.get(product) == 1) {
                products.remove(product);
            }
        }
    }

    /**
     * @return unmodifiable copy of the map
     */
    @Override
    public Map<Product, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(products);
    }

    /**
     * Checkout will rollback if there is not enough of some product in stock
     * Checkout will rollback if there is not enough User credit
     * @throws InsufficientStockException
     */
    @Override
    public void checkout() throws InsufficientStockException, InsufficientCreditException {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            deductStock(entry);
        }

        deductCredit();

        productRepository.save(products.keySet());
        productRepository.flush();
        products.clear();
    }

    /**
     * Deduct stock from selected entry in Map
     * @param entry
     * @throws InsufficientStockException
     */
    private void deductStock(Map.Entry<Product, Integer> entry) throws InsufficientStockException {
        // Refresh quantity for every product before checking
        Product product = productRepository.findOne(entry.getKey().getId());
        if (product.getQuantity() < entry.getValue()) {
            throw new InsufficientStockException(product);
        }
        entry.getKey().setQuantity(product.getQuantity() - entry.getValue());
    }

    /**
     * Deduct credit from User credit
     * Total shopping cart amount will deducted from User credit
     *
     * @throws InsufficientCreditException
     */
    private void deductCredit() throws InsufficientCreditException {
        User loggedInUser = userRepository.findByUsername(getLoggedInUserName());
        if (getTotal().compareTo(loggedInUser.getCreditAmount()) > 0) {
            throw new InsufficientCreditException(loggedInUser);
        }

        loggedInUser.getCreditAmount().subtract(getTotal());

        userRepository.save(loggedInUser);
        userRepository.flush();
    }

    private String getLoggedInUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @Override
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Integer productQuantity = entry.getValue();
            BigDecimal productPrice = entry.getKey().getPrice().multiply(new BigDecimal(productQuantity));
            total = total.add(productPrice);
        }
        return total;
    }
}
