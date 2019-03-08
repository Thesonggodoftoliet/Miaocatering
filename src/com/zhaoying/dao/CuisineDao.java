package com.zhaoying.dao;

import com.zhaoying.instance.Cuisine;

import java.util.List;

public interface CuisineDao {
    List<Cuisine> getAllCuisine(String exasql);
    List<Cuisine> getCuisineByResId(int id);
    Cuisine getCuisineById(int id);
    Cuisine addCuisine(Cuisine cuisine);
    boolean updateCuiById(Cuisine cuisine);
    int getNumOfCuisine();
    boolean deleteCuiById(int id);
}
