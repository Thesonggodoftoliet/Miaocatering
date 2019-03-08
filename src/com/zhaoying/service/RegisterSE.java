package com.zhaoying.service;

import com.zhaoying.instance.Restaurant;
import com.zhaoying.instance.User;

public interface RegisterSE {
    String addUser(User tempUser);
    int addRestaurant(Restaurant restaurant);
}
