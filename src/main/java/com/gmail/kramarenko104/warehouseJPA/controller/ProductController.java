package com.gmail.kramarenko104.warehouseJPA.controller;

import com.gmail.kramarenko104.warehouseJPA.model.Product;
import com.gmail.kramarenko104.warehouseJPA.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping()
    public String retrieveAllProducts(Map<String, Object> model) {
        System.out.println("ProductController: retrieve all Products...");
        Iterable<Product> products = productRepository.findAll();
        model.put("products", products);
        // showproducts ==  means template file 'showproducts.mustache'
        return "showproducts";
    }

    @GetMapping("/{id}")
    public String retrievePproduct(
            @PathVariable long id,
            Map<String, Object> model) {
        System.out.println("ProductController: retrieve Product by id = " + id);
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product foundProduct = product.get();
            System.out.println("ProductController: found Product: " + product.toString());
            model.put("products", foundProduct);
        }
        return "showproducts";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(
            @PathVariable long id,
            Map<String, Object> model) {
        System.out.println("ProductController: delete Product by id = " + id);
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
        }
        // show updated data
        Iterable<Product> allProducts = productRepository.findAll();
        model.put("products", allProducts);
        return "showproducts";
    }

    @PostMapping("/add")
    public String addProduct(
            @RequestParam String mod,
            @RequestParam double price,
            Map<String, Object> model) {
        System.out.println("ProductController: try to create new Product... ");
        Product savedProduct = new Product();
        savedProduct.setModel(mod);
        savedProduct.setPrice(price);
        System.out.println("ProductController: created new product: " + savedProduct.toString());
        productRepository.save(savedProduct);
        //model.put("products", savedProduct);

        // show updated data
        Iterable<Product> allProducts = productRepository.findAll();
        model.put("products", allProducts);
        return "showproducts";
    }
}
