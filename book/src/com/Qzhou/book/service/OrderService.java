package com.Qzhou.book.service;

import com.Qzhou.book.pojo.OrderBean;
import com.Qzhou.book.pojo.User;

import java.util.List;

public interface OrderService {
    void addOrderBean(OrderBean orderBean);
    List<OrderBean> getOrderList(User user);

}
