package com.example.max.ebook.main;

import com.example.max.ebook.data.RemoteBookStack;
import com.example.max.ebook.data.Book;
import com.example.max.ebook.user.User;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by chapmac on 4/20/2017 AD.
 */

public class BookPresenter implements Observer{
    RemoteBookStack repository;
    private BookView view;
    User user;

    public BookPresenter(BookView view){
        this.view = view;
        this.user = new User();
    }

    public void listAllBook(){
        repository = RemoteBookStack.getInstance();
        repository.addObserver(this);
        repository.fetchAllBooks();
    }

    @Override
    public void update(Observable o, Object arg) {
        List<Book> books = repository.getAllBooks();
        view.setBookResult(books);

    }

    public List<Book> sort(List<Book> books,String state) {
        Book temp;
        if(state.equals("by Published year")){
            for (int i = 0;i<books.size()-1;i++) {
                for (int j = i+1;j<books.size();j++) {
                    if (books.get(i).getPub_year() > books.get(j).getPub_year()) {
                        temp = books.get(j);
                        books.set(j,books.get(i));
                        books.set(i,temp);
                    }
                }
            }
        } else {
            for (int i = 0;i<books.size()-1;i++) {
                for (int j = i+1;j<books.size();j++) {
                    if (books.get(i).getTitle().charAt(0) > books.get(j).getTitle().charAt(0)) {
                        temp = books.get(j);
                        books.set(j, books.get(i));
                        books.set(i, temp);
                    }
                }
            }

        }

        return books;

    }
    public void search(String text,String state) {
        List<Book> books = repository.getBooks(text);
        books = sort(books, state);
        view.setBookResult(books);
    }

    public void setMoney(double money){
        user.money= money;
    }

    public void setTotalPrice(double totalPrice){
        user.order.totalPrice = totalPrice;
    }


    public void addToCart(int position) {
        user.addToOrder(repository.getBookById(position));
    }
}

