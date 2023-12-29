package com.example.webmvc.service;

import com.example.webmvc.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Boolean create(Category category);

    Category findById(Integer id);

    Boolean update(Category category);
    Boolean delete(Integer integer);

    List<Category> searchCategory(String keyword);

    Page<Category> getAll(Integer pageNo);
    Page<Category> searchCategory(String keyword,Integer pageNo);
}
