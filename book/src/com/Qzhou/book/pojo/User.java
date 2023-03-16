package com.Qzhou.book.pojo;

import java.util.List;

public class User {
    private Integer id;
    private String uname;
    private String pwd;
    private Integer role;
    private String email;
    private Cart cart;
    private List<OrderBean> orderList;

    public List<OrderBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderBean> orderList) {
        this.orderList = orderList;
    }

    public User() {
    }

    public User(String uname, String pwd, String email) {
        this.uname = uname;
        this.pwd = pwd;
        this.email = email;
    }

    public User(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String uname, String pwd, Integer role, String email) {
        this.uname = uname;
        this.pwd = pwd;
        this.role = role;
        this.email = email;
    }


    public User(Integer id, String uname, String pwd, Integer role, String email) {
        this.id = id;
        this.uname = uname;
        this.pwd = pwd;
        this.role = role;
        this.email = email;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
