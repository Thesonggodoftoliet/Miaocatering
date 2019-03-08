package com.zhaoying.service;

import com.zhaoying.instance.User;

public interface UpdateUserInfoSE {
    String setUser(User tuser);
    String collectCuisine(User tuser);
    String collectRestaurant(User tuser);
    String deleteUser(int id);
}
