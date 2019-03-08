package com.zhaoying.dao.DaoImpl;

import com.zhaoying.dao.OrderDao;
import com.zhaoying.instance.Order;
import com.zhaoying.utils.JdbcUtils;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public Order getOrderById(int id) {
        String sql = "select * from order_table where id=?";
        return (Order) JdbcUtils.getObjectById(Order.class,sql,id);
    }

    @Override
    public List<Order> getAllOder() {
        String sql = "select * from order_table";
        return JdbcUtils.getList(Order.class,sql);
    }

    @Override
    public List<Order> getOrderByResId(int resID) {
        String sql = "select * from order_table where restau_id = "+resID;
        return JdbcUtils.getList(Order.class,sql);
    }

    @Override
    public List<Order> getOrderByUserId(int userID) {
        String sql = "select * from order_table where buyer_id ="+userID;
        return JdbcUtils.getList(Order.class,sql);
    }

    @Override
    public Order addOrder(Order order) {
        String sql = "insert into order_table values(?,?,?,?,?,?,?,?,?)";
        int tag = JdbcUtils.executeSQL(sql,order.getId(),order.getCuisine_id(),order.getRestau_id(),order.getCtime(),order.getComment(),order.getBuyer_id(),order.getOrder_status(),order.getTot_price(),order.getAppoint_time());
        if (tag == 0)
            return null;
        else
            return order;
    }

    @Override
    public boolean updateOrderById(Order order) {
        String sql = "update order_table set comment =?,order_status=? where id = ?";
        int tag = JdbcUtils.executeSQL(sql,order.getComment(),order.getOrder_status(),order.getId());
        if (tag == 0)
            return false;
        else
            return true;
    }

    @Override
    public boolean deleteOrderById(int id) {
        String slq= "delete from order_table where id = ?";
        int tag = JdbcUtils.executeSQL(slq,id);
        if (tag == 0)
            return false;
        else
            return true;
    }

    @Override
    public int getMumofOrder() {
        String sql = "select * from order_table";
        return JdbcUtils.getListCount(sql);
    }
}
