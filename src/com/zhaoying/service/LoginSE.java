package com.zhaoying.service;

import com.zhaoying.instance.Admin;
import com.zhaoying.instance.Restaurant;
import com.zhaoying.instance.User;

public interface LoginSE {
    String userCheck(User tuser);

    String adminCheck(Admin admin);

    String retaurantCheck(Restaurant restaurant);
}
