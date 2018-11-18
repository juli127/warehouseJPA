package com.gmail.kramarenko104.warehouseJPA.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private String name;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String address;

    @Column
    private boolean active;

    public Client(long id, String name, String login, String password, String address) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.address = address;
    }

    public Client(String name, String login, String password, String address) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.address = address;
    }

    public Client() {
    }

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", address='" + address + '\'' +
                ", active=" + active +
                ", roles=" + roles +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return getId() == client.getId() &&
                isActive() == client.isActive() &&
                Objects.equals(getName(), client.getName()) &&
                Objects.equals(getLogin(), client.getLogin()) &&
                Objects.equals(password, client.password) &&
                Objects.equals(getAddress(), client.getAddress()) &&
                Objects.equals(getRoles(), client.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLogin(), password, getAddress(), isActive(), getRoles());
    }
}
