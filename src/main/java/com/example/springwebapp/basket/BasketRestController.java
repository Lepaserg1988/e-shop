package com.example.springwebapp.basket;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BasketRestController {


    @GetMapping("/addToBasket")
    Integer addProductToBasket(HttpServletRequest request, @RequestParam Long productId) {
        HttpSession session = request.getSession();
        Map<Long, Integer> basket = (Map<Long, Integer>) session.getAttribute("basket");
        if(basket == null) {
            basket = new HashMap<>();
            session.setAttribute("basket", basket);
        }
        int productCount = 1;
        if(basket.get(productId) != null){
            productCount = basket.get(productId) + 1;
        }
        basket.put(productId, productCount);
        return productCount;
    }
}
