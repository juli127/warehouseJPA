package com.gmail.kramarenko104.warehouseJPA.controller;

import com.gmail.kramarenko104.warehouseJPA.entity.Purchase;
import com.gmail.kramarenko104.warehouseJPA.repository.PurchaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    PurchaseRepo purchaseRepo;

    @GetMapping()
    public String retrieveAllPurchases(Map<String, Object> model) {
        System.out.println("PurchaseController: retrieve all Purchases...");
        Iterable<Purchase> purchases = purchaseRepo.findAll();
        model.put("purchases", purchases);
        // showpurchases ==  means template file 'showpurchases.mustache'
        return "showpurchases";
    }

    @GetMapping("/{id}")
    public String retrievePurchase(
            @PathVariable long id,
            Map<String, Object> model) {
        System.out.println("PurchaseController: retrieve Purchase by id = " + id);
        Optional<Purchase> purchase = purchaseRepo.findById(id);
        if (purchase.isPresent()) {
            Purchase foundPurchase = purchase.get();
            System.out.println("PurchaseController: found Purchase: " + purchase.toString());
            model.put("purchases", foundPurchase);
        }
        return "showpurchases";
    }

//    List<Purchase> getPurchasesByClient(String login){
//        return null;
//    }

    @DeleteMapping("/{id}")
    public String deletePurchase(
            @PathVariable long id,
            Map<String, Object> model) {
        System.out.println("PurchaseController: delete Purchase by id = " + id);
        Optional<Purchase> purchase = purchaseRepo.findById(id);
        if (purchase.isPresent()) {
            purchaseRepo.deleteById(id);
        }
        // show updated data
        Iterable<Purchase> allPurchases = purchaseRepo.findAll();
        model.put("purchases", allPurchases);
        return "showpurchases";
    }

    @PostMapping()
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

        purchaseRepo.save(newPurchase);
       // model.put("purchases", newPurchase);

        // show updated data
        Iterable<Purchase> allPurchases = purchaseRepo.findAll();
        model.put("purchases", allPurchases);
        return "showpurchases";
    }
}
