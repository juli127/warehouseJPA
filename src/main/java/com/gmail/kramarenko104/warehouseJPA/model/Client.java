package com.gmail.kramarenko104.warehouseJPA.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private String name;

    @Column
    private String address;

//    @ElementCollection
//    Set<Client> clients = new HashSet<>();

    public Client(long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
       // clients.add(this);
    }

    public Client(String name, String address) {
//        if (clients.isEmpty()){
//            this.id = 0;
//        }else {
//            this.id = clients.size();
//        }
        this.name = name;
        this.address = address;
       // clients.add(this);
    }

    public Client() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(getName(), client.getName()) &&
                Objects.equals(getAddress(), client.getAddress());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "[name='" + name + ", address='" + address + "]";
    }
}
