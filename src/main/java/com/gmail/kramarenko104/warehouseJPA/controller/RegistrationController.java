package com.gmail.kramarenko104.warehouseJPA.controller;

import com.gmail.kramarenko104.warehouseJPA.entity.Client;
import com.gmail.kramarenko104.warehouseJPA.entity.Role;
import com.gmail.kramarenko104.warehouseJPA.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private ClientRepo clientRepo;

    @GetMapping("/registration")
    public String registration() {
        System.out.println("RegistrationController:Enter GetMapping /registration  ");
        return "registration";
    }

    @PostMapping("/registration")
    public String addClient(Client client, Map<String, Object> model) {
        System.out.println("RegistrationController: Enter PostMapping /registration for Client: " + client.toString());
        Client clientFromDb = clientRepo.findByLogin(client.getLogin());

        if (clientFromDb != null) {
            System.out.println("Such client already exists!");
            model.put("message", "Client exists!");
            return "registration";
        }

        client.setActive(true);
        client.setRoles(Collections.singleton(Role.USER));
        clientRepo.save(client);
        System.out.println("New client was registered... go to login page ..." );

        return "redirect:/login";
    }
}
