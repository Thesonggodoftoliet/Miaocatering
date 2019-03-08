package com.zhaoying.service.impl;

import com.zhaoying.dao.RestaurantDao;
import com.zhaoying.dao.DaoImpl.RestaurantDaoImpl;
import com.zhaoying.dao.DaoImpl.UserDaoImpl;
import com.zhaoying.instance.Restaurant;
import com.zhaoying.instance.User;
import com.zhaoying.service.RegisterSE;

import java.util.List;

public class RegisterSEImpl implements RegisterSE {
    @Override
    public String addUser(User tempUser) {
        UserDaoImpl UDI = new UserDaoImpl();
        List<User>  users= UDI.getAllUser();
        if (users.size() == 0)//没有用户
            tempUser.setId(1);
        else
            tempUser.setId(users.get(users.size()-1).getId()+1);//最后用户的ID+1

        if(UDI.getUserByPhone(tempUser.getPhone())==null) {
            UDI.addUser(tempUser);
            return Integer.toString(tempUser.getId());
        }
        else {
            return "0";//有重复账号
        }
    }

    @Override
    public int addRestaurant(Restaurant restaurant) {
        RestaurantDao rd = new RestaurantDaoImpl();
        List<Restaurant> restaurants = rd.getAllRes("");
        if (restaurants.size() == 0)
            restaurant.setId(rd.getNumOfRes()+1);
        else
            restaurant.setId(restaurants.get(restaurants.size()-1).getId()+1);

        if (rd.getResByPhone(restaurant.getPhone()) == null) {
            rd.addRestaurant(restaurant);
            return restaurant.getId();
        }else {
            return 0;//重复注册;
        }
    }
}
