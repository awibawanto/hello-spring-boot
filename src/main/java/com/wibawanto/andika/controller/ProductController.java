package com.wibawanto.andika.controller;

import com.wibawanto.andika.model.Product;
import com.wibawanto.andika.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author andika
 */
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<Product> greeting(@RequestParam("pageSize") Optional<Integer> pageSize,
                             @RequestParam("page") Optional<Integer> page) {
        // Evaluate page size. If requested parameter is null, return initial page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);

        // Evaluate page. If requested parameter is null or less than 0 (to prevent exception),
        // return initial size. Otherwise, return value of param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        return productService.findAll(new PageRequest(evalPage, evalPageSize));
    }
}
