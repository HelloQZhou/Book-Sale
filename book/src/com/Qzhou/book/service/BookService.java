package com.Qzhou.book.service;

import com.Qzhou.book.pojo.Book;

import java.util.List;

public interface BookService {
    List<Book> getBookList();
    Book getBookById(Integer id);

}
