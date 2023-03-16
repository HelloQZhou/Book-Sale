package com.Qzhou.book.service.Impl;

import com.Qzhou.book.dao.CartItemDAO;
import com.Qzhou.book.dao.OrderDAO;
import com.Qzhou.book.dao.OrderItemDAO;
import com.Qzhou.book.pojo.CartItem;
import com.Qzhou.book.pojo.OrderBean;
import com.Qzhou.book.pojo.OrderItem;
import com.Qzhou.book.pojo.User;
import com.Qzhou.book.service.OrderService;

import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;
    private CartItemDAO cartItemDAO;
    private Integer getOrderTotalBookCount;
    @Override
    public void addOrderBean(OrderBean orderBean) {
        /*
        * 1、订单表添加一条记录 t_order
        * 2、订单详情表添加一条记录 t_order_item
        * 3、购物车项表删除对应的N条记录 t_cart_item
        * */
        //1
        //执行为这一步orderBean 中的 id 是有值的
        orderDAO.addOrderBean(orderBean);

        //2
        //此时OrderBean 中的 orderItemList 是空的 此处我们应该根据用户购物车中的购物车项去转换成一个一个的订单项
        User currUser = orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemMap = currUser.getCart().getCartItemMap();
        for (CartItem cartItem:cartItemMap.values()){
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemDAO.addOrderItem(orderItem);
        }

        //3
        for (CartItem cartItem:cartItemMap.values()){
            cartItemDAO.delCartItem(cartItem);
        }

    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        List<OrderBean> orderList = orderDAO.getOrderList(user);
        for(OrderBean orderBean: orderList){
            Integer totalBookCount = orderDAO.getTotalBookCount(orderBean);
            orderBean.setTotalCount(totalBookCount);
        }
        return orderList;
    }
}
