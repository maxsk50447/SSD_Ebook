package com.example.max.ebook.user;

import com.example.max.ebook.data.Book;

import java.util.ArrayList;

/**
 * Created by Max on 3/6/2560.
 */

public class Order {
    public ArrayList<Book> books;
    public double totalPrice;
    public ArrayList<String> orderList;

    public Order() {
        books = new ArrayList<Book>();
        totalPrice = 0;
        orderList = new ArrayList<String>();
    }
    public void addToOrder(Book book) {
        books.add(book);
        totalPrice += book.getPrice();
    }
    public ArrayList<Book> getBooks(){
        return books;
    }

    public ArrayList<String> getOrderList(){
        return orderList;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }
    public void clear() {
        this.totalPrice = 0;
        orderList.clear();
        books.clear();
    }
    public void addOrder(String book){
        orderList.add(book);
    }
}
