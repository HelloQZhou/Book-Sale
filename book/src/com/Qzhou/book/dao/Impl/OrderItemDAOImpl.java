package com.Qzhou.book.dao.Impl;

import com.Qzhou.book.dao.OrderItemDAO;
import com.Qzhou.book.pojo.OrderItem;
import com.Qzhou.myssm.basedao.BaseDAO;

public class OrderItemDAOImpl extends BaseDAO<OrderItem> implements OrderItemDAO {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        super.executeUpdate("insert into t_order_item values(0,?,?,?)",orderItem.getBook().getId(),orderItem.getBuyCount(),orderItem.getOrderBean().getId());
    }
}
