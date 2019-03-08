package com.zhaoying.service;

import com.zhaoying.instance.*;

import java.util.List;

public interface GetInfoSE {
    User getUserInfoById(int userid);
    List<User> getAllUser();

    Admin getAdminInfoById(int adminid);
    Cuisine getCuisineInfoById(int id);
    List<Cuisine> getAllCuisine(String exasql);
    List<Cuisine> getCuisineByResId(int id);
    List<Cuisine> getCuisineByName(int restau_id,String name);

    Restaurant getRestaurantById(int resid);
    List<Restaurant> getAllRestaurant(String exasql);

    List<Swiper> getAllSwiper();

    Order getOrderById(int id);
    List<Order> getAllOrderByUserId(int id);
    List<Order> getAllOrderByResId(int id);
}
