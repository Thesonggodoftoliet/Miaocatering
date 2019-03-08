package com.zhaoying.service;

import com.zhaoying.instance.Cuisine;

public interface UpdateCuiInfoSE {
    int addCuisine(Cuisine cuisine);
    String setCuisine(Cuisine cuisine);
    String deleteCuiById(int id);
    boolean beCollected(int id);
    boolean beSold(int id);
}
