package com.gmail.kramarenko104.warehouseJPA.repository;

import com.gmail.kramarenko104.warehouseJPA.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface ClientRepository extends JpaRepository<Client, Long> {
public interface ClientRepository extends CrudRepository<Client, Long> {

}
