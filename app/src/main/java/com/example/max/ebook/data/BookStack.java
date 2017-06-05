package com.example.max.ebook.data;

import com.example.max.ebook.data.Book;

import java.util.List;
import java.util.Observable;

/**
 * Created by Max on 27/4/2560.
 */

public abstract class BookStack extends Observable {
    public abstract List<Book> getAllBooks();
    public abstract List<Book> getBooks(String bookName);


}
