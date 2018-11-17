package com.gmail.kramarenko104.warehouseJPA.controller;

import com.gmail.kramarenko104.warehouseJPA.exceptions.ClientNotFoundException;
import com.gmail.kramarenko104.warehouseJPA.model.Client;
import com.gmail.kramarenko104.warehouseJPA.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients")
    public String retrieveAllClients(Map<String, Object> model) {
        System.out.println("ClientController: retrieve all Clients...");
        Iterable<Client> clients = clientRepository.findAll();
        model.put("clients", clients);
        // showclients ==  means template file 'showclients.mustache'
        return "showclients";
    }

    @GetMapping("/clients/{id}")
    public String retrieveClient(
            @PathVariable long id,
            Map<String, Object> model) {
        System.out.println("ClientController: retrieve Client by id = " + id);
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            Client foundClient = client.get();
            System.out.println("ClientController: found Client: " + client.toString());
            model.put("clients", foundClient);
        }
        return "showclients";
    }

    @DeleteMapping("/clients/delete/{id}")
    public String deleteClient(
            @PathVariable long id,
            Map<String, Object> model) {
        System.out.println("ClientResource: deleteClient by id = "+ id);
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            clientRepository.deleteById(id);
        }
        // show updated data
        Iterable<Client> allClients = clientRepository.findAll();
        model.put("clients", allClients);
        return "showclients";
    }

    //localhost:8080/clients/add?name="First"&address="someTestAddress"
    @PostMapping("/clients/add")
    public String createClient(
            @RequestParam String name,
            @RequestParam String address,
            Map<String, Object> model) {
        System.out.println("ClientController: try to create new Client... ");
        Client newClient = new Client();
        newClient.setName(name);
        newClient.setAddress(address);
        System.out.println("ClientController: created new Client: " + newClient.toString());

        Client savedClient = clientRepository.save(newClient);
        //model.put("clients", savedClient);

        // show updated data
        Iterable<Client> allClients = clientRepository.findAll();
        model.put("clients", allClients);
        return "showclients";
    }

    //localhost:8080/clients/update/4/name="Forth"&address="4 some TestAddress"
    @PutMapping("/clients/update/{id}")
   // public ResponseEntity<Object> updateClient(
    public String updateClient(
            @PathVariable long id,
            @RequestParam String updName,
            @RequestParam String updAddress,
            Map<String, Object> model) {
        System.out.println("ClientResource: updateClient with id = " + id);
        Optional<Client> clientOptional = clientRepository.findById(id);

        if (clientOptional.isPresent()) {

            Client updatedClient = clientOptional.get();
            updatedClient.setName(updName);
            updatedClient.setName(updAddress);

            clientRepository.save(updatedClient);
            //model.put("clients", updatedClient);
        }

        // show updated data
        Iterable<Client> allClients = clientRepository.findAll();
        model.put("clients", allClients);
        return "showclients";
        //return ResponseEntity.noContent().build();
    }

}
