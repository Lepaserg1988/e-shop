package com.example.springwebapp.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Denis on 2/20/2016.
 */

@Controller
public class ProductController {

    @RequestMapping("/")
    public String homeWithSession(Model model, HttpSession session) {
        int totalProductCount = 0;
        Map<Long, Integer> basket = (Map<Long, Integer>) session.getAttribute("basket");
        if(basket != null){
            for(Map.Entry<Long, Integer> entry : basket.entrySet()) {
                totalProductCount += entry.getValue();
            }
        }
        model.addAttribute("totalProductCount", totalProductCount);
        return "index";
    }

}
