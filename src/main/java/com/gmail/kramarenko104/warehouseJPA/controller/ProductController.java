package com.gmail.kramarenko104.warehouseJPA.controller;

import com.gmail.kramarenko104.warehouseJPA.model.Product;
import com.gmail.kramarenko104.warehouseJPA.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String retrieveAllProducts(Map<String, Object> model) {
        System.out.println("ProductController: retrieve all Products...");
        Iterable<Product> products = productRepository.findAll();
        model.put("products", products);
        // showproducts ==  means template file 'showproducts.mustache'
        return "showproducts";
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public String retrievePproduct(@PathVariable long id, Map<String, Object> model) {
        System.out.println("ProductController: retrieve Product by id = " + id);
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product foundProduct = product.get();
            System.out.println("ProductController: found Product: " + product.toString());
            model.put("products", foundProduct);
        }
        return "showproducts";
    }

    @DeleteMapping("/products/delete/{id}")
    public void deleteProduct(@PathVariable long id, Map<String, Object> model) {
        System.out.println("ProductController: delete Product by id = " + id);
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            retrieveAllProducts(model);
        }
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.POST)
    public String addProduct(
            @RequestParam (value = "model", required = true) String mod,
            @RequestParam (value = "price", required = true) double price,
            Map<String, Object> model) {
        System.out.println("ProductController: try to create new Product... ");
        Product product = new Product();
        product.setModel(mod);
        product.setPrice(price);
        System.out.println("ProductController: created new product: " + product.toString());
        productRepository.save(product);
        model.put("products", product);
        return "showproducts";
    }
}
