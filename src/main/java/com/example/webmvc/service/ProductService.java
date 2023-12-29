package com.example.webmvc.service;

import com.example.webmvc.model.Category;
import com.example.webmvc.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();
    Boolean create(Product product);

    Product findById(Integer id);

    Boolean update(Product product);
    Boolean delete(Integer integer);
}
