package com.example.webmvc.controller.admin;

import com.example.webmvc.model.Category;
import com.example.webmvc.model.Product;
import com.example.webmvc.service.CategoryService;
import com.example.webmvc.service.ProductService;
import com.example.webmvc.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/product")
    public String  index(Model model){

        List<Product> listCategory = this.productService.getAll();
        model.addAttribute("listCategory", listCategory);

        return "admin/product/index";
    }

    @RequestMapping("/product-add")
    public String  add(Model model){
        Product product = new Product();
        model.addAttribute("product",product);

        List<Category> listCate = this.categoryService.getAll();
        model.addAttribute("listCate",listCate);

        return "admin/product/add";
    }

    @PostMapping("/product-addd")
    public String save(@ModelAttribute("product") Product product, @RequestParam("fileImage") MultipartFile file){


        // upload file
        this.storageService.store(file);
        String fileName = file.getOriginalFilename();
        product.setImage(fileName);
        if (this.productService.create(product)){
            return "redirect:/admin/product";
        }

        return "admin/product/add";
    }


    @GetMapping("/edit-product/{id}")
    public String edit(Model model, @PathVariable("id") Integer id){


        List<Category> listCate = this.categoryService.getAll();
        model.addAttribute("listCate",listCate);

        Product product = this.productService.findById(id);
        model.addAttribute("product",product);



        return "admin/product/edit";
    }

    @PostMapping("/edit-product")
    public String update(@ModelAttribute ("product") Product product){
        if (this.productService.create(product))
        {
            return "redirect:/admin/product";
        }
        else
            return "admin/product/index";
    }


    @GetMapping("/delete_product/{id}")
    public String delete( @PathVariable("id") Integer id){
        if (this.productService.delete(id)){
            return "redirect:/admin/product";
        }
        else
            return "redirect:/admin/product";
    }



}
