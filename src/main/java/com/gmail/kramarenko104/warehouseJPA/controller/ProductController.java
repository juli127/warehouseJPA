package com.gmail.kramarenko104.warehouseJPA.controller;

import com.gmail.kramarenko104.warehouseJPA.entity.Product;
import com.gmail.kramarenko104.warehouseJPA.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepo productRepo;

    @GetMapping()
    public String retrieveAllProducts(Map<String, Object> model) {
        System.out.println("ProductController: retrieve all Products...");
        Iterable<Product> products = productRepo.findAll();
        model.put("products", products);
        // showproducts ==  means template file 'showproducts.mustache'
        return "showproducts";
    }

    @GetMapping("/{id}")
    public String retrieveProduct(
            @PathVariable long id,
            Map<String, Object> model) {
        System.out.println("ProductController: retrieve Product by id = " + id);
        Optional<Product> product = productRepo.findById(id);
        if (product.isPresent()) {
            Product foundProduct = product.get();
            System.out.println("ProductController: found Product: " + product.toString());
            model.put("products", foundProduct);
        }
        return "showproducts";
    }

    // HttpRequestMethodNotSupportedException: Request method 'GET' not supported]
    @DeleteMapping("delete/{id}")
    public String deleteProduct(
            @PathVariable long id,
            Map<String, Object> model) {
        System.out.println("ProductController: delete Product by id = " + id);
        Optional<Product> product = productRepo.findById(id);
        if (product.isPresent()) {
            productRepo.deleteById(id);
        }
        // show updated data
        Iterable<Product> allProducts = productRepo.findAll();
        model.put("products", allProducts);
        return "showproducts";
    }

    //.w.s.m.s.DefaultHandlerExceptionResolver :
    // Resolved [org.springframework.web.HttpRequestMethodNotSupportedException:
    // Request method 'POST' not supported]
    @PostMapping("/add")
    public String addProduct(
            @RequestParam String type,
            @RequestParam (name = "model") String mod,
            @RequestParam double price,
            Map<String, Object> model) {
        System.out.println("ProductController: try to create new Product... ");
        Product savedProduct = new Product(type, mod, price);
        System.out.println("ProductController: created new product: " + savedProduct.toString());
        productRepo.save(savedProduct);

        Iterable<Product> allProducts = productRepo.findAll();
        model.put("products", allProducts);
        return "showproducts";
    }
}
