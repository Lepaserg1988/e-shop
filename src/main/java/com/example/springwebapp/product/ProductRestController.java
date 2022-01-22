package com.example.springwebapp.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductRestController {
    final ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    List<Product> all(@RequestParam(required = false) String searchText, @RequestParam(required = false) Integer sortOrder) {
        if(searchText == null || searchText.isEmpty()){
            return Integer.valueOf(0).equals(sortOrder)
                    ? productRepository.findByOrderByPriceAsc()
                    : productRepository.findByOrderByPriceDesc();
        } else {
            return Integer.valueOf(0).equals(sortOrder)
                    ? productRepository.findByNameContainsOrderByPriceAsc(searchText)
                    : productRepository.findByNameContainsOrderByPriceDesc(searchText);
        }

    }


}
