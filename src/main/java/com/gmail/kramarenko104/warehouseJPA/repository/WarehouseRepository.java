package com.gmail.kramarenko104.warehouseJPA.repository;

import com.gmail.kramarenko104.warehouseJPA.model.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends CrudRepository<WareHouse, Long> {

}