package com.gmail.kramarenko104.warehouseJPA.repository;

import com.gmail.kramarenko104.warehouseJPA.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
