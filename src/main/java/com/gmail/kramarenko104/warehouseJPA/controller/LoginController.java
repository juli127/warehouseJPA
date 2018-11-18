package com.gmail.kramarenko104.warehouseJPA.controller;

import com.gmail.kramarenko104.warehouseJPA.entity.Client;
import com.gmail.kramarenko104.warehouseJPA.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private ClientRepo clientRepo;

    @GetMapping("/login")
    public String login() {
        System.out.println("LoginController: Enter GetMapping /login  ");
        return "login";
    }

    @PostMapping("/login")
    public String addUser(Client client, Map<String, Object> model) {
        System.out.println("Enter PostMapping /login for client: " + client.toString());
        Client clientFromDb = clientRepo.findByLogin(client.getLogin());

        if (clientFromDb != null) {
            System.out.println("Such client exists!");
            model.put("message", "Client exists!");
            return "redirect:/login?login";
        }

        System.out.println("Such client does not exist!... go to registration page ..." );

        return "redirect:/login?logout";
    }
}
