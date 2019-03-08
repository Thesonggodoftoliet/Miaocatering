package com.zhaoying.service.impl;

import com.zhaoying.dao.DaoImpl.OrderDaoImpl;
import com.zhaoying.dao.OrderDao;
import com.zhaoying.instance.Cuisine;
import com.zhaoying.instance.Order;
import com.zhaoying.service.UpdateOrderInfoSE;

import java.util.List;

public class UpdateOrderInfoSEImpl implements UpdateOrderInfoSE {
    @Override
    public int addOrder(Order order) {
        OrderDao orderDao = new OrderDaoImpl();
        List<Order> orders = orderDao.getAllOder();
        if (orders.size() == 0)
            order.setId(orderDao.getMumofOrder()+1);
        else
            order.setId(orders.get(orders.size()-1).getId()+1);
        if (orderDao.addOrder(order) == null)
            return 0;
        else
            return order.getId();
    }

    @Override
    public String changeOrderStatu(Order order) {
        OrderDao orderDao = new OrderDaoImpl();
        Order sqlorder = orderDao.getOrderById(order.getId());
        if (sqlorder == null)
            return "-1";
        else {
            if (order.getComment() != null)
                if (sqlorder.getComment() == null)
                    sqlorder.setComment(order.getComment());
                else if (sqlorder.getComment().equals(order.getComment()))
                    sqlorder.setComment(order.getComment());

            if (order.getOrder_status()<2)
                sqlorder.setOrder_status(sqlorder.getOrder_status()+1);
            else if (order.getOrder_status() == 3)
                sqlorder.setOrder_status(3);
        }
        if (orderDao.updateOrderById(sqlorder))
            return "1";
        else
            return "0";
    }

    @Override
    public String deleteOrderById(int id) {
        OrderDao orderDao = new OrderDaoImpl();
        if (orderDao.deleteOrderById(id))
            return "1";
        else
            return "0";
    }

    @Override
    public int CaculatePrice(List<Cuisine> list) {
        int price =0;
        for (int i = 0;i<list.size();i++){
            price+=list.get(i).getPrice();
        }
        return price;
    }
}
