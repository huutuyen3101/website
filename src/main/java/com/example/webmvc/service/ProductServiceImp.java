package com.example.webmvc.service;

import com.example.webmvc.model.Product;
import com.example.webmvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Boolean create(Product product) {
        try {
                this.productRepository.save(product);
                return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Product findById(Integer id) {
        return this.productRepository.findById(id).get();
    }

    @Override
    public Boolean update(Product product) {
        try {
            this.productRepository.save(product);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Boolean delete(Integer id) {

        try {
            this.productRepository.delete(findById(id));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
