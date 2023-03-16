package com.Qzhou.book.pojo;

import java.math.BigDecimal;

public class CartItem {
    private Integer id;
    private Book book;
    private Integer buyCount;
    private User userBean;
    private Double xj;

    public CartItem() {
    }

    public CartItem(Integer id, Integer buyCount) {
        this.id = id;
        this.buyCount = buyCount;
    }

    public CartItem(Book book, Integer buyCount, User userBean) {
        this.book = book;
        this.buyCount = buyCount;
        this.userBean = userBean;
    }

    public CartItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public User getUserBean() {
        return userBean;
    }

    public void setUserBean(User userBean) {
        this.userBean = userBean;
    }

    public Double getXj() {
        BigDecimal bigDecimalPrice = new BigDecimal(""+getBook().getPrice());
        BigDecimal bigDecimalByCount = new BigDecimal("" + buyCount);
        BigDecimal bigDecimalXj = bigDecimalPrice.multiply(bigDecimalByCount);
        xj=bigDecimalXj.doubleValue();
        return xj;
    }

}
