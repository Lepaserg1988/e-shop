package com.example.springwebapp.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByOrderByPriceAsc();

    List<Product> findByOrderByPriceDesc();

    List<Product> findByNameContainsOrderByPriceAsc(String text);

    List<Product> findByNameContainsOrderByPriceDesc(String text);

}
