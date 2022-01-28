package com.example.springwebapp.basket;

import com.example.springwebapp.product.Product;
import com.example.springwebapp.product.ProductRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class BasketRestController {
    private final ProductRepository productRepository;

    public BasketRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PutMapping("/addToBasket")
    Integer addProductToBasket(HttpServletRequest request, @RequestParam Long productId) {
        Map<Long, Integer> basket = getBasket(request);
        int productCount = 1;
        if(basket.get(productId) != null){
            productCount = basket.get(productId) + 1;
        }
        basket.put(productId, productCount);
        return productCount;
    }

    @PutMapping("/reduceFromBasket")
    Integer reduceProductFromBasket(HttpServletRequest request, @RequestParam Long productId) {
        Map<Long, Integer> basket = getBasket(request);
        int productCount = 0;
        Integer savedProductCount = basket.get(productId);
        if(savedProductCount != null && savedProductCount > 0){
            productCount = basket.get(productId) - 1;
        }
        basket.put(productId, productCount);
        return productCount;
    }

    @DeleteMapping("/deleteFromBasket")
    void deleteProductFromBasket(HttpServletRequest request, @RequestParam Long productId) {
        Map<Long, Integer> basket = getBasket(request);
        basket.remove(productId);
    }

    @GetMapping("/getProductInBasket")
    List<ProductInBasket> getProductInBasket(HttpServletRequest request) {
        List<ProductInBasket> result = new ArrayList<>();
        Map<Long, Integer> basket = getBasket(request);
        for(Map.Entry<Long, Integer> entry : basket.entrySet()) {
            Optional<Product> product = productRepository.findById(entry.getKey());
            product.ifPresent(prod -> {
                ProductInBasket productInBasket = new ProductInBasket();
                productInBasket.setId(prod.getId());
                productInBasket.setName(prod.getName());
                productInBasket.setPhotoUrl(prod.getPhotoUrl());
                productInBasket.setPrice(prod.getPrice());
                productInBasket.setCount(entry.getValue());
                result.add(productInBasket);
            });
        }
        return result;
    }

    private Map<Long, Integer> getBasket(HttpServletRequest request){
        HttpSession session = request.getSession();
        Map<Long, Integer> basket = (Map<Long, Integer>) session.getAttribute("basket");
        if(basket == null) {
            basket = new HashMap<>();
            session.setAttribute("basket", basket);
        }
        return basket;
    }
}
