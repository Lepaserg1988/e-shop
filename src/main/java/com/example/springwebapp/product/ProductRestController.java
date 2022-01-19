package com.example.springwebapp.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductRestController {

    @GetMapping("/products")
    List<Product> all() {
        return ProductRepository.products;
    }


}
