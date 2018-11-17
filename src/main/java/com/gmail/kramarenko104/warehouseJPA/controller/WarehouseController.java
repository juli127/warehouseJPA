package com.gmail.kramarenko104.warehouseJPA.controller;

import com.gmail.kramarenko104.warehouseJPA.model.WareHouse;
import com.gmail.kramarenko104.warehouseJPA.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @GetMapping()
    public String retrieveAllProductsOnStore(Map<String, Object> model) {
        System.out.println("WarehouseController: retrieve all Products on store...");
        Iterable<WareHouse> warehouse = warehouseRepository.findAll();
        model.put("warehouse", warehouse);
        return "showWarehouse";
    }


}
