package com.example.max.ebook.data;

import android.support.annotation.NonNull;

/**
 * Created by Max on 26/4/2560.
 */

public class Book {
    private double price;
    private int id;
    private String title;
    private int pub_year;
    private String img_url;

    public Book(String title, int id, double price, int pub_year, String img_url){
        this.id = id;
        this.price = price;
        this.title = title;
        this.pub_year = pub_year;
        this.img_url = img_url;
    }

    public int getId(){
        return  this.id;
    }

    public double getPrice(){
        return this.price;
    }

    public String getTitle(){
        return this.title;
    }

    public String getUrl(){
        return this.img_url;
    }

    public int getPub_year() {return this.pub_year;}

    public String toString() {
        return getTitle() + " " + getPub_year();
    }
    public void setTitle(String title){
        this.title = title;
    }
}
