package com.Qzhou.book.controller;

import com.Qzhou.book.pojo.OrderBean;
import com.Qzhou.book.pojo.User;
import com.Qzhou.book.service.OrderService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderController {
    private OrderService orderService;
    //结账
    public String checkout(HttpSession session){
        OrderBean orderBean = new OrderBean();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        //format.split("-");
        orderBean.setOrderNo(UUID.randomUUID().toString()+"_"+format);
        orderBean.setOrderDate(date);

        User currUser = (User)session.getAttribute("currUser");
        orderBean.setOrderUser(currUser);
        orderBean.setOrderMoney(currUser.getCart().getTotalMoney());
        orderBean.setOrderStatus(0);
        orderService.addOrderBean(orderBean);

        return "index";
    }
    //查看订单列表
    public String getOrderList(HttpSession session){
        User currUser = (User) session.getAttribute("currUser");

        List<OrderBean> orderList = orderService.getOrderList(currUser);
        currUser.setOrderList(orderList);
        session.setAttribute("currUser",currUser);
        return "order/order";
    }

}
