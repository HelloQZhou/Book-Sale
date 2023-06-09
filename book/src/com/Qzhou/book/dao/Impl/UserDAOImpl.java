package com.Qzhou.book.dao.Impl;

import com.Qzhou.book.dao.UserDAO;
import com.Qzhou.myssm.basedao.BaseDAO;
import com.Qzhou.book.pojo.User;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    @Override
    public User getUser(String uname, String pwd) {
        return load("select * from t_user where uname like ? and pwd like ?",uname,pwd);
    }

    @Override
    public void addUser(User user) {
        executeUpdate("insert into t_user values(0,?,?,?,0)",user.getUname(),user.getPwd(),user.getEmail());
    }
}
