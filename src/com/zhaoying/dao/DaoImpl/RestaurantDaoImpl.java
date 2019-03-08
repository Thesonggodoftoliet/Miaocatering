package com.zhaoying.dao.DaoImpl;

import com.zhaoying.dao.RestaurantDao;
import com.zhaoying.instance.Restaurant;
import com.zhaoying.utils.JdbcUtils;

import java.util.List;

public class RestaurantDaoImpl implements RestaurantDao {
    @Override
    public List<Restaurant> getAllRes(String exasql) {
        String sql = "select * from restaurant_table "+exasql;
        return JdbcUtils.getList(Restaurant.class,sql);
    }

    @Override
    public Restaurant getResById(int id) {
        String sql = "select * from restaurant_table where id = ?";
        return (Restaurant) JdbcUtils.getObjectById(Restaurant.class,sql,id);
    }

    @Override
    public Restaurant getResByPhone(String phone) {
        String sql = "select * from restaurant_table where phone = ?";
        return (Restaurant) JdbcUtils.getObjectById(Restaurant.class,sql,phone);
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        String sql = "insert into restaurant_table values(?,?,?,?,?,?,?,?,?,?,?)";
        int tag =JdbcUtils.executeSQL(sql,restaurant.getId(),restaurant.getRestauname(),restaurant.getAddress(),restaurant.getPhone(),restaurant.getPasswd(),restaurant.getCtime(),restaurant.getCollect_num(),restaurant.getDescripion(),restaurant.getLicence(),restaurant.getCover_url(),restaurant.getSale_info());
        if (tag == 0) return null;
        else return restaurant;
    }

    @Override
    public int getNumOfRes() {
        String sql = "select * from restaurant_table";
        return JdbcUtils.getListCount(sql);
    }

    @Override
    public boolean updateResById(Restaurant restaurant) {
        String sql = "update restaurant_table set restauname=?,address=?,phone=?,passwd=?,collect_num=?,descripion=?,licence=?,cover_url=?,sale_info=? where id =?";
        int tag = JdbcUtils.executeSQL(sql,restaurant.getRestauname(),restaurant.getAddress(),restaurant.getPhone(),restaurant.getPasswd(),restaurant.getCollect_num(),restaurant.getDescripion(),restaurant.getLicence(),restaurant.getCover_url(),restaurant.getSale_info(),restaurant.getId());
        if (tag == 0)return false;
        else return true;
    }

    @Override
    public boolean deleteResById(int id) {
        String sql = "delete from restaurant_table where id = ?";
        int tag = JdbcUtils.executeSQL(sql,id);
        if (tag == 0)return false;
        else return true;
    }
}
