package com.example.max.ebook.user;

import com.example.max.ebook.data.Book;

import java.util.ArrayList;

/**
 * Created by Max on 3/6/2560.
 */

public class User {
    public Order order;
    public double money;
    public ArrayList<String> collectionList;

    public User() {
        order = new Order();
        money = 0;
        collectionList = new ArrayList<String>();
    }
    public void refill(double amount) {
        this.money+= amount;
    }
    public double getMoney() {
        return this.money;
    }

    public void addToOrder(Book book) {
        order.addToOrder(book);
    }
    public void purchase() {
        if (money>=order.getTotalPrice()) {
            money -= order.getTotalPrice();
            for (int i = 0; i < order.orderList.size(); i++) {
                collectionList.add(order.orderList.get(i));
            }
            order.clear();
        }
    }
    public ArrayList<String> getCollectionList() {
        return this.collectionList;
    }
    public void addCollection(String book){
        collectionList.add(book);
    }

}
