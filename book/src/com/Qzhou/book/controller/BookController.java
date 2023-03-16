package com.Qzhou.book.controller;

import com.Qzhou.book.pojo.Book;
import com.Qzhou.book.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class BookController {
    private BookService bookService;
    public String index(HttpSession session){

        List<Book> bookList = bookService.getBookList();
        session.setAttribute("bookList",bookList);
        return "index";
    }
}
