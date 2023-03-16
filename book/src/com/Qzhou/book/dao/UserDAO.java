package com.Qzhou.book.dao;

import com.Qzhou.book.pojo.User;

public interface UserDAO {

    User getUser(String uname, String pwd);

    void addUser(User user);
}
