package com.zhaoying.dao;

import com.zhaoying.instance.Restaurant;

import java.util.List;

public interface RestaurantDao {
    List<Restaurant> getAllRes(String exasql);
    Restaurant getResById(int id);
    Restaurant getResByPhone(String phone);
    Restaurant addRestaurant(Restaurant restaurant);
    int getNumOfRes();
    boolean updateResById(Restaurant restaurant);
    boolean deleteResById(int id);
}
