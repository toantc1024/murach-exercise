package com.murach.dto;

import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;

@Entity
public class User {

    @Id
    @Column(name="user_id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String lastName;
    private String email;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cart_id", referencedColumnName = "id")
    private Cart cart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public User() {
    }

    public User(String lastName, String email, String password) {
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(String lastName, String email, String password, Cart cart) {
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.cart = cart;
    }
}
