package com.zhaoying.service;

import com.zhaoying.instance.Cuisine;
import com.zhaoying.instance.Order;

import java.util.List;

public interface UpdateOrderInfoSE {
    int addOrder(Order order);
    String changeOrderStatu(Order order);
    String deleteOrderById(int id);
    int CaculatePrice(List<Cuisine> list);
}
