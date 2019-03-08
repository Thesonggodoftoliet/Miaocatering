package com.zhaoying.service.impl;

import com.zhaoying.dao.CuisineDao;
import com.zhaoying.dao.DaoImpl.CuisineDaoImpl;
import com.zhaoying.dao.RestaurantDao;
import com.zhaoying.dao.DaoImpl.RestaurantDaoImpl;
import com.zhaoying.instance.Cuisine;
import com.zhaoying.instance.Restaurant;
import com.zhaoying.service.GetInfoSE;
import com.zhaoying.service.UpdateResInfoSE;
import com.zhaoying.utils.PraseUtils;

import java.util.List;

public class UpdateResInfoSEImpl implements UpdateResInfoSE {
    @Override
    public String setRestaurant(Restaurant restaurant) {
        RestaurantDao rd= new RestaurantDaoImpl();
        Restaurant sqlres = rd.getResById(restaurant.getId());
        if (sqlres == null) return "-1";
        else {
            if (restaurant.getRestauname() != null)
                if (sqlres.getRestauname() == null)
                    sqlres.setRestauname(restaurant.getRestauname());
                else if (!sqlres.getRestauname().equals(restaurant.getRestauname()))
                    sqlres.setRestauname(restaurant.getRestauname());
            if (restaurant.getCover_url() != null)
                if (sqlres.getCover_url() == null)
                    sqlres.setCover_url(restaurant.getCover_url());
                else if (!sqlres.getCover_url().equals(restaurant.getCover_url()))
                    sqlres.setCover_url(restaurant.getCover_url());
            if (restaurant.getDescripion() != null)
                if (sqlres.getDescripion() == null)
                    sqlres.setDescripion(restaurant.getDescripion());
                else if (!sqlres.getDescripion().equals(restaurant.getDescripion()))
                    sqlres.setDescripion(restaurant.getDescripion());
            if (restaurant.getPasswd() != null )
                if (sqlres.getPasswd() == null)
                    sqlres.setPasswd(restaurant.getPasswd());
                else if (!sqlres.getPasswd().equals(restaurant.getPasswd()))
                    sqlres.setPasswd(restaurant.getPasswd());
            if (restaurant.getAddress() != null)
                if (sqlres.getAddress() == null)
                    sqlres.setAddress(restaurant.getAddress());
                else if ( !sqlres.getAddress().equals(restaurant.getAddress()))
                    sqlres.setAddress(restaurant.getAddress());
            if (restaurant.getPhone() != null)
                if (sqlres.getPhone() == null)
                    sqlres.setPhone(restaurant.getPhone());
                else if ( !sqlres.getPhone().equals(restaurant.getPhone()))
                    sqlres.setPhone(restaurant.getPhone());
            if (restaurant.getCollect_num() != sqlres.getCollect_num() && (restaurant.getCollect_num() !=0))
                sqlres.setCollect_num(restaurant.getCollect_num());
            if (restaurant.getSale_info() != null)
                if (sqlres.getSale_info() ==null)
                    sqlres.setSale_info(restaurant.getSale_info());
                else if (!sqlres.getSale_info().equals(restaurant.getSale_info()))
                    sqlres.setSale_info(restaurant.getSale_info());
        }
        if (rd.updateResById(sqlres))
            return "1";
        else return "0";
    }

    @Override
    public String deleteResById(int id) {
        RestaurantDao rd = new RestaurantDaoImpl();
        GetInfoSE getInfoSE = new GetInfoSeImpl();
        List<Cuisine> cuisine= getInfoSE.getCuisineByResId(id);
            CuisineDao cd = new CuisineDaoImpl();
            while (cuisine.size() != 0)
                cd.deleteCuiById(cuisine.remove(0).getId());
        if(rd.deleteResById(id)) return "1";
        else return "0";
    }

    @Override
    public boolean beCollected(int id) {
        RestaurantDao restaurantDao = new RestaurantDaoImpl();
        Restaurant sqlrestaurant = restaurantDao.getResById(id);
        sqlrestaurant.setCollect_num(sqlrestaurant.getCollect_num()+1);
        if (restaurantDao.updateResById(sqlrestaurant))
            return true;
        else
            return false;
    }
}
