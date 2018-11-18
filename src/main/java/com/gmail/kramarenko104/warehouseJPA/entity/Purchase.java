package com.gmail.kramarenko104.warehouseJPA.entity;

import javax.persistence.*;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long cl_id;

    private long prod_id;

    private int amount;

    public Purchase() {}

    public Purchase(long id, long cl_id, long prod_id, int amount) {
        this.id = id;
        this.cl_id = cl_id;
        this.prod_id = prod_id;
        this.amount = amount;
    }

    public Purchase(long cl_id, long prod_id, int amount) {
        this.cl_id = cl_id;
        this.prod_id = prod_id;
        this.amount = amount;
    }

    @Column(name="id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name="cl_id")
    public long getClientId() {
        return cl_id;
    }

    public void setClientId(long cl_id) {
        this.cl_id = cl_id;
    }

    @Column(name="prod_id")
    public long getProductId() {
        return prod_id;
    }

    public void setProductId(long prod_id) {
        this.prod_id = prod_id;
    }

    @Column(name="amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return getAmount() == purchase.getAmount() &&
                cl_id == purchase.getClientId() &&
                prod_id == purchase.getProductId();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) cl_id;
        result = prime * result + (int)prod_id;
        result = prime * result + amount;
        return result;
    }

}
