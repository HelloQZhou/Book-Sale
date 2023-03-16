package com.Qzhou.book.dao.Impl;

import com.Qzhou.book.dao.BookDAO;
import com.Qzhou.myssm.basedao.BaseDAO;
import com.Qzhou.book.pojo.Book;

import java.util.List;

public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {
    @Override
    public List<Book> getBookList() {
       return executeQuery("select * from t_book where bookStatus=0");
//        return executeQuery("select * from t_book where bookStatus=0");

    }

    @Override
    public Book getBookById(Integer id) {
        return load("select * from t_book where id=?",id);
    }
}
