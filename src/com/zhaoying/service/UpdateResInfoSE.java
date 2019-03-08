package com.zhaoying.service;

import com.zhaoying.instance.Restaurant;

public interface UpdateResInfoSE {
    String setRestaurant(Restaurant restaurant);
    String deleteResById(int id);
    boolean beCollected(int id);

}
