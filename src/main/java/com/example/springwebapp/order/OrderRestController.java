package com.example.springwebapp.order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderRestController {
    private final OrderRepository orderRepository;

    public OrderRestController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping("/saveOrder")
    void saveOrder(HttpServletRequest request) {
        Map<Long, Integer> basket = getBasket(request);
        Map<Long, Integer> productIdMap = new HashMap<>();
        for(Map.Entry<Long, Integer> entry : basket.entrySet()) {
            if(entry.getValue() > 0){
                productIdMap.put(entry.getKey(), entry.getValue());
            }
        }
        BasketOrder order = new BasketOrder();
        Cookie cityCookie = WebUtils.getCookie(request, "city");
        if (cityCookie != null) {
            order.setCityId(Long.valueOf(cityCookie.getValue()));
        }
        order.setProductIdList(productIdMap);
        order.setOrderDate(new Date().getTime());
        orderRepository.save(order);
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
