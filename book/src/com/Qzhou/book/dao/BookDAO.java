package com.Qzhou.book.dao;

import com.Qzhou.book.pojo.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getBookList();
    Book getBookById(Integer id);
}
