package com.gmail.kramarenko104.warehouseJPA.controller;

import com.gmail.kramarenko104.warehouseJPA.entity.Client;
import com.gmail.kramarenko104.warehouseJPA.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientRepo clientRepo;

    //localhost:8080/clients ++++++++++++
    @GetMapping()
    public String retrieveAllClients(Map<String, Object> model) {
        System.out.println("ClientController: ENTER retrieve all Clients...");
        Iterable<Client> clients = clientRepo.findAll();
        model.put("clients", clients);
        // showclients ==  means template file 'showclients.mustache'
        return "showclients";
    }

    //localhost:8080/clients/2 ++++++++++++
    @GetMapping("/{id}")
    public String retrieveClient(
            @PathVariable long id,
            Map<String, Object> model) {
        System.out.println("ClientController: retrieve Client by id = " + id);
        Optional<Client> client = clientRepo.findById(id);
        if (client.isPresent()) {
            Client foundClient = client.get();
            System.out.println("ClientController: found Client: " + client.toString());
            model.put("clients", foundClient);
        }
        return "showclients";
    }

    //localhost:8080/clients/add?name="First"&login="jin44"&password="111"&address="someTestAddress" ->
    //org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'POST' not supported
    //Exception thrown when a request handler does not support a specific request method.
    @PostMapping("/add")
    public String addClient(
            @RequestParam String name,
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String address,
            Map<String, Object> model) {
        System.out.println("ClientController: POST....try to create new Client... ");
        Client newClient = new Client(name, login, password, address);
        newClient.setActive(true);
        clientRepo.save(newClient);

        Iterable<Client> allClients = clientRepo.findAll();
        model.put("clients", allClients);
        return "showclients";
    }

    @PutMapping("update/{id}")
    public String updateClient(
            @PathVariable long id,
            @RequestParam String updName,
            @RequestParam String updLogin,
            @RequestParam String updPassw,
            @RequestParam String updAddress,
            Map<String, Object> model) {
        System.out.println("ClientResource: updateClient with id = " + id);
        Optional<Client> clientOptional = clientRepo.findById(id);

        if (clientOptional.isPresent()) {
            Client updatedClient = clientOptional.get();
            updatedClient.setName(updName);
            updatedClient.setLogin(updLogin);
            updatedClient.setPassword(updPassw);
            updatedClient.setAddress(updAddress);
            updatedClient.setActive(true);
            clientRepo.save(updatedClient);
        }
        // show updated data
        Iterable<Client> allClients = clientRepo.findAll();
        model.put("clients", allClients);
        return "showclients";

    }

    //localhost:8080/clients/delete/4
    // HttpRequestMethodNotSupportedException: Request method 'GET' not supported
    @DeleteMapping("/delete/{id}")
    public String deleteClient(
            @PathVariable long id,
            Map<String, Object> model) {
        System.out.println("ClientResource: deleteClient by id = "+ id);
        Optional<Client> client = clientRepo.findById(id);
        if (client.isPresent()) {
            clientRepo.deleteById(id);
        }
        Iterable<Client> allClients = clientRepo.findAll();
        model.put("clients", allClients);
        return "showclients";
    }

}
