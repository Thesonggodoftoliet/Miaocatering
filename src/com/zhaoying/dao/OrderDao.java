package com.zhaoying.dao;

import com.zhaoying.instance.Order;

import java.util.List;

public interface OrderDao {
    Order getOrderById(int id);
    List<Order> getAllOder();
    List<Order> getOrderByResId(int resID);
    List<Order> getOrderByUserId(int userID);
    Order addOrder(Order order);//返回ID
    boolean updateOrderById(Order order);
    boolean deleteOrderById(int id);
    int getMumofOrder();
}
