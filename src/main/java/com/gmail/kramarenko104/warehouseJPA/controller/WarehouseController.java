package com.gmail.kramarenko104.warehouseJPA.controller;

import com.gmail.kramarenko104.warehouseJPA.entity.WareHouse;
import com.gmail.kramarenko104.warehouseJPA.repository.WarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseRepo warehouseRepo;

    @GetMapping()
    public String retrieveAllProductsOnStore(Map<String, Object> model) {
        System.out.println("WarehouseController: retrieve all Products on store...");
        Iterable<WareHouse> warehouse = warehouseRepo.findAll();
        model.put("warehouse", warehouse);
        return "showWarehouse";
    }


}
