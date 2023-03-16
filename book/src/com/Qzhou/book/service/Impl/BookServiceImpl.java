package com.Qzhou.book.service.Impl;

import com.Qzhou.book.dao.BookDAO;
import com.Qzhou.book.service.BookService;
import com.Qzhou.book.pojo.Book;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;

    @Override
    public List<Book> getBookList() {
        return bookDAO.getBookList();
    }

    @Override
    public Book getBookById(Integer id) {

        return  bookDAO.getBookById(id);

    }
}
