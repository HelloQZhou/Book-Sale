package com.Qzhou.book.dao;

import com.Qzhou.book.pojo.OrderBean;
import com.Qzhou.book.pojo.User;

import java.util.List;

public interface OrderDAO {
    //添加订单
    void addOrderBean(OrderBean orderBean);
    //获取指定用户的订单列表
    List<OrderBean> getOrderList(User user);
    //获取book 总数量
    Integer getTotalBookCount(OrderBean orderBean);
}
