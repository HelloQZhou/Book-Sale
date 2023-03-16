package com.Qzhou.book.service;

import com.Qzhou.book.pojo.User;

public interface UserService {
    User login(String uname,String pwd);
    void regist(User user);

}
