package com.gmail.kramarenko104.warehouseJPA.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String type;

    private String model;

    private double price;

    @ElementCollection
    Set<Product> products = new HashSet<>();

    public Product(){
    }

    public Product(String type, String model, double price) {
        this.type = type;
        this.model = model;
        this.price = price;
        if (products.isEmpty()) {
            this.id = 0;
        } else {
            this.id = products.size();
        }
        products.add(this);
    }

    public Product(long id, String type, String model, double price) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.price = price;
    }

    @Column(name="id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name="type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name="model")
    public String geModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name="price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 &&
                Objects.equals(getType(), product.getType()) &&
                Objects.equals(model, product.model);
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = (int) (31 * result + price);
        return result;
    }

    @Override
    public String toString() {
        return "['" + type + "', model='" + model + "', price=" + price + "]";
    }


}

