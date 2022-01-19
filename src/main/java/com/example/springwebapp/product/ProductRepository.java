package com.example.springwebapp.product;

import java.util.ArrayList;

public class ProductRepository {

    public static ArrayList<Product> products = new ArrayList<>();

    static {
        Product prod1 = new Product();
        prod1.setId(1L);
        prod1.setName("Машина");
        prod1.setDescription("Обычная красная машина");
        prod1.setPrice(100);
        prod1.setPhotoUrl("1.jpg");
        Product prod2 = new Product();
        prod2.setId(2L);
        prod2.setName("Машина2");
        prod2.setDescription("Крутая черная машина");
        prod2.setPrice(250);
        prod2.setPhotoUrl("1.jpg");
        products.add(prod1);
        products.add(prod2);
    }
}
