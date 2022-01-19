package com.example.springwebapp.basket;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class BasketController {

    @RequestMapping("/basket")
    public String basket(Model model, HttpSession session) {
        Map<Long, Integer> basket = (Map<Long, Integer>) session.getAttribute("basket");
        return "basket";
    }
}
