package com.wibawanto.andika.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author andika
 */
@Controller
public class DefaultController {

    @GetMapping("/403")
    public String error403() {
        return "/403";
    }

}
