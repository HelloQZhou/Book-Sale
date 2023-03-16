package com.Qzhou.book.service.Impl;

import com.Qzhou.book.dao.UserDAO;
import com.Qzhou.book.pojo.User;
import com.Qzhou.book.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    @Override
    public User login(String uname, String pwd) {
        return userDAO.getUser(uname,pwd);
    }

    @Override
    public void regist(User user) {
        userDAO.addUser(user);
    }
}
