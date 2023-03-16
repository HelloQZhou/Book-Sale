package com.Qzhou.book.dao.Impl;

import com.Qzhou.book.dao.OrderDAO;
import com.Qzhou.book.dao.OrderItemDAO;
import com.Qzhou.book.pojo.OrderBean;
import com.Qzhou.book.pojo.OrderItem;
import com.Qzhou.book.pojo.User;
import com.Qzhou.myssm.basedao.BaseDAO;

import java.math.BigDecimal;
import java.util.List;

public class OrderDAOImpl extends BaseDAO<OrderBean> implements OrderDAO {

    @Override
    public void addOrderBean(OrderBean orderBean) {
        int orderBeanId=super.executeUpdate("insert into t_order values(0,?,?,?,?,?)",orderBean.getOrderNo(),orderBean.getOrderDate(),orderBean.getOrderUser().getId(),orderBean.getOrderMoney(),orderBean.getOrderStatus());
        orderBean.setId(orderBeanId);
        //此时需要接受到返回的 orderBeanId 然后设置到 orderBean 中 id 属性上
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        return executeQuery("select * from t_order where orderUser=?",user.getId());
    }

    @Override
    public Integer getTotalBookCount(OrderBean orderBean) {
        String sql="SELECT SUM(t3.buyCount) AS totalCount ,t3.orderBean FROM\n" +
                "(\n" +
                "SELECT t1.id,t2.buyCount,t2.orderBean \n" +
                "FROM t_order t1\n" +
                "INNER JOIN t_order_item t2\n" +
                "on t1.id=t2.orderBean \n" +
                "WHERE t1.orderUser=?\n" +
                ") t3\n" +
                "WHERE t3.orderBean=?\n" +
                "GROUP BY t3.orderBean";
       // return ((Long)executeComplexQuery(sql,orderBean.getOrderUser().getId(),orderBean.getId())[0]).intValue();
        return ((BigDecimal)executeComplexQuery(sql,orderBean.getOrderUser().getId(),orderBean.getId())[0]).intValue();
    }
}
