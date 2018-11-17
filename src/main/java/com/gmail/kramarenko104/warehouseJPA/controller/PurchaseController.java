package com.gmail.kramarenko104.warehouseJPA.controller;

import com.gmail.kramarenko104.warehouseJPA.model.Purchase;
import com.gmail.kramarenko104.warehouseJPA.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    PurchaseRepository purchaseRepository;

    @GetMapping()
    public String retrieveAllPurchases(Map<String, Object> model) {
        System.out.println("PurchaseController: retrieve all Purchases...");
        Iterable<Purchase> purchases = purchaseRepository.findAll();
        model.put("purchases", purchases);
        // showpurchases ==  means template file 'showpurchases.mustache'
        return "showpurchases";
    }

    @GetMapping("/{id}")
    public String retrievePurchase(
            @PathVariable long id,
            Map<String, Object> model) {
        System.out.println("PurchaseController: retrieve Purchase by id = " + id);
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        if (purchase.isPresent()) {
            Purchase foundPurchase = purchase.get();
            System.out.println("PurchaseController: found Purchase: " + purchase.toString());
            model.put("purchases", foundPurchase);
        }
        return "showpurchases";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePurchase(
            @PathVariable long id,
            Map<String, Object> model) {
        System.out.println("PurchaseController: delete Purchase by id = " + id);
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        if (purchase.isPresent()) {
            purchaseRepository.deleteById(id);
        }
        // show updated data
        Iterable<Purchase> allPurchases = purchaseRepository.findAll();
        model.put("purchases", allPurchases);
        return "showpurchases";
    }

    @PostMapping("/add")
    String addPurchase(
            @RequestParam long cl_id,
            @RequestParam long prod_id,
            @RequestParam int amount,
            Map<String, Object> model) {
        System.out.println("PurchaseController: try to create new Purchase... ");
        Purchase newPurchase = new Purchase();
        newPurchase.setClientId(cl_id);
        newPurchase.setProductId(prod_id);
        newPurchase.setAmount(amount);
        System.out.println("PurchaseController: created new purchase: " + newPurchase.toString());

        purchaseRepository.save(newPurchase);
       // model.put("purchases", newPurchase);

        // show updated data
        Iterable<Purchase> allPurchases = purchaseRepository.findAll();
        model.put("purchases", allPurchases);
        return "showpurchases";
    }
}
