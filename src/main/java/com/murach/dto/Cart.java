package com.murach.dto;

import com.murach.dao.UserDAO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,  orphanRemoval = true, fetch=FetchType.EAGER)
    private List<LineItem> items = new ArrayList<LineItem>();


    public Cart() {
    }

    public int getTotalItems() {
        int total = 0;
        for (LineItem item : items) {
            total += item.getQuantity();
        }
        return total;
    }

    public Cart(User user) {
        this.user = user;
        this.items = new ArrayList<LineItem>();
    }

    public void addItem(LineItem item) {
        items.add(item);
        item.setCart(this);
    }

    public void removeItem(LineItem item) {
        items.remove(item);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<LineItem> getItems() {
        return items;
    }

    public void setItems(List<LineItem> items) {
        this.items = items;
    }
}
