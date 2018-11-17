package com.gmail.kramarenko104.warehouseJPA.controller;

import com.gmail.kramarenko104.warehouseJPA.model.Purchase;
import com.gmail.kramarenko104.warehouseJPA.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
public class PurchaseController {

    @Autowired
    PurchaseRepository purchaseRepository;

    @RequestMapping(value = "/purchases", method = RequestMethod.GET)
    public String retrieveAllPurchases(Map<String, Object> model) {
        System.out.println("PurchaseController: retrieve all Purchases...");
        Iterable<Purchase> purchases = purchaseRepository.findAll();
        model.put("purchases", purchases);
        // showpurchases ==  means template file 'showpurchases.mustache'
        return "showpurchases";
    }

    @RequestMapping(value = "/purchases/{id}", method = RequestMethod.GET)
    public String retrievePurchase(@PathVariable long id, Map<String, Object> model) {
        System.out.println("PurchaseController: retrieve Purchase by id = " + id);
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        if (purchase.isPresent()) {
            Purchase foundPurchase = purchase.get();
            System.out.println("PurchaseController: found Purchase: " + purchase.toString());
            model.put("purchases", foundPurchase);
        }
        return "showpurchases";
    }

    @DeleteMapping("/purchases/delete/{id}")
    public void deletePurchase(@PathVariable long id, Map<String, Object> model) {
        System.out.println("PurchaseController: delete Purchase by id = " + id);
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        if (purchase.isPresent()) {
            purchaseRepository.deleteById(id);
            retrieveAllPurchases(model);
        }
    }

    @PostMapping("/purchases/add")
    public @ResponseBody
    String addPurchase(
            @RequestParam long cl_id,
            @RequestParam long prod_id,
            @RequestParam int amount,
            Map<String, Object> model) {
        System.out.println("PurchaseController: try to create new Purchase... ");
        Purchase purchase = new Purchase();
        purchase.setClientId(cl_id);
        purchase.setProductId(prod_id);
        purchase.setAmount(amount);
        System.out.println("PurchaseController: created new purchase: " + purchase.toString());
        purchaseRepository.save(purchase);
        model.put("purchases", purchase);
        return "showpurchases";
    }
}
