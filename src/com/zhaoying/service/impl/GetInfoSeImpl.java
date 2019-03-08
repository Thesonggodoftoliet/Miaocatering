package com.zhaoying.service.impl;

import com.jieba.JiebaSegmenter;
import com.zhaoying.dao.*;
import com.zhaoying.dao.DaoImpl.*;
import com.zhaoying.instance.*;
import com.zhaoying.service.GetInfoSE;
import com.zhaoying.utils.JdbcUtils;
import com.zhaoying.utils.PraseUtils;
import com.zhaoying.utils.SearchUtils;

import java.util.List;

public class GetInfoSeImpl implements GetInfoSE {
    @Override
    public User getUserInfoById(int userid) {
        UserDao ud = new UserDaoImpl();
        return ud.getUserById(userid);
    }

    @Override
    public List<User> getAllUser() {
        UserDao ud = new UserDaoImpl();
        return ud.getAllUser();
    }

    @Override
    public Admin getAdminInfoById(int adminid) {
        AdminDao adminDao = new AdminDaoImpl();
        return adminDao.getAdminById(adminid);
    }

    @Override
    public Cuisine getCuisineInfoById(int id) {
        CuisineDao cd = new CuisineDaoImpl();
        return cd.getCuisineById(id);
    }

    @Override
    public List<Cuisine> getAllCuisine(String exasql) {
        CuisineDao cuisineDao  = new CuisineDaoImpl();
        return cuisineDao.getAllCuisine(exasql);
    }

    @Override
    public List<Cuisine> getCuisineByResId(int id) {
        CuisineDao cuisineDao = new CuisineDaoImpl();
        return cuisineDao.getCuisineByResId(id);
    }

    @Override
    public List<Cuisine> getCuisineByName(int restau_id,String name) {
        Restaurant restaurant = getRestaurantById(restau_id);
            List<Cuisine> allcuisines =getCuisineByResId(restau_id);
            int i = 0;
            List<String> temp;
            JiebaSegmenter segmenter = new JiebaSegmenter();
            temp = segmenter.sentenceProcess(name);
            if (SearchUtils.getCuisine(name,allcuisines).isEmpty()){
                while (SearchUtils.getCuisine(temp.get(i),allcuisines).isEmpty())
                    i++;
                System.out.println("分词大小"+temp.size()+" i="+i);
                if (i>=temp.size())
                    return null;
                return SearchUtils.getCuisine(temp.get(i),allcuisines);
            }else {
                return SearchUtils.getCuisine(name,allcuisines);
            }
    }

    @Override
    public Restaurant getRestaurantById(int resid) {
        RestaurantDao rd = new RestaurantDaoImpl();
        return rd.getResById(resid);
    }

    @Override
    public List<Restaurant> getAllRestaurant(String exasql) {
        RestaurantDao rd = new RestaurantDaoImpl();
        return rd.getAllRes(exasql);
    }

    @Override
    public List<Swiper> getAllSwiper() {
        SwiperDao swiperDao = new SwiperDaoImpl();
        return swiperDao.getAllSwiper();
    }

    @Override
    public Order getOrderById(int id) {
        OrderDao orderDao = new OrderDaoImpl();
        return orderDao.getOrderById(id);
    }

    @Override
    public List<Order> getAllOrderByUserId(int id) {
        OrderDao orderDao = new OrderDaoImpl();
        return orderDao.getOrderByUserId(id);
    }

    @Override
    public List<Order> getAllOrderByResId(int id) {
        OrderDao orderDao = new OrderDaoImpl();
        return orderDao.getOrderByResId(id);
    }

}
