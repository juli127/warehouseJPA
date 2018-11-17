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
public class WarehouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @RequestMapping(value = "/warehouse", method = RequestMethod.GET)
    public String retrieveAllClients(Map<String, Object> model) {
        System.out.println("ClientController: retrieve all Clients...");
        Iterable<WareHouse> warehouse = warehouseRepository.findAll();
        model.put("warehouse", warehouse);
        // showWarehouse ==  means template file 'showWarehouse.mustache'
        return "showWarehouse";
    }


}
