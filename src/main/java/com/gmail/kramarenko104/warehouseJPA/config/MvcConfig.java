package com.gmail.kramarenko104.warehouseJPA.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("choice");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/choice").setViewName("choice");
        registry.addViewController("/clients").setViewName("showclients");
        registry.addViewController("/products").setViewName("showproducts");
        registry.addViewController("/purchases").setViewName("showpurchases");
        registry.addViewController("/warehouse").setViewName("showWarehouse");
    }

}