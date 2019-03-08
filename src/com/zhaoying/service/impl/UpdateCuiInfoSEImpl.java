package com.zhaoying.service.impl;

import com.zhaoying.dao.CuisineDao;
import com.zhaoying.dao.DaoImpl.CuisineDaoImpl;
import com.zhaoying.instance.Cuisine;
import com.zhaoying.instance.Restaurant;
import com.zhaoying.instance.User;
import com.zhaoying.service.UpdateCuiInfoSE;
import com.zhaoying.service.UpdateResInfoSE;

import java.util.List;

public class UpdateCuiInfoSEImpl implements UpdateCuiInfoSE {
    @Override
    public int addCuisine(Cuisine cuisine) {
        CuisineDao cuisineDao = new CuisineDaoImpl();
        List<Cuisine> cuisines = cuisineDao.getAllCuisine("");
        Restaurant restaurant =new Restaurant();
        restaurant.setId(cuisine.getRestau_id());
        System.out.println("restaurantID="+restaurant.getId());

        UpdateResInfoSE updateResInfoSE = new UpdateResInfoSEImpl();

        if (cuisines.size() ==0) {
            cuisine.setId(cuisineDao.getNumOfCuisine() + 1);
            System.out.println("报错信息  "+updateResInfoSE.setRestaurant(restaurant));
        }
        else{
            cuisine.setId(cuisines.get(cuisines.size()-1).getId()+1);
            System.out.println("报错信息  "+updateResInfoSE.setRestaurant(restaurant));
        }
        if (cuisineDao.addCuisine(cuisine) == null)
            return 0;
        else
            return cuisine.getId();
    }

    @Override
    public String setCuisine(Cuisine cuisine) {
        CuisineDao cd = new CuisineDaoImpl();
        Cuisine sqlcui = cd.getCuisineById(cuisine.getId());
        if (sqlcui == null) return "-1";
        else {
            if (cuisine.getDetail_url() != null)//如果传入的数据不为空并且和数据库不同则进行修改
                if (sqlcui.getDetail_url() == null)
                    sqlcui.setDetail_url(cuisine.getDetail_url());
                else if (!sqlcui.getDetail_url().equals(cuisine.getDetail_url()))
                    sqlcui.setDetail_url(cuisine.getDetail_url());
            if (cuisine.getCover_url()!= null)
                if (sqlcui.getCover_url() == null)
                    sqlcui.setCover_url(cuisine.getCover_url());
                else if (!sqlcui.getCover_url().equals(cuisine.getCover_url()))
                    sqlcui.setCover_url(cuisine.getCover_url());
            if (cuisine.getC_name() != null)
                if (sqlcui.getC_name() == null)
                    sqlcui.setC_name(cuisine.getC_name());
                else if (!sqlcui.getC_name().equals(cuisine.getC_name()))
                    sqlcui.setC_name(cuisine.getC_name());
            if ((cuisine.getOrigin_price() != sqlcui.getOrigin_price()) && (cuisine.getOrigin_price() !=0))
                sqlcui.setOrigin_price(cuisine.getOrigin_price());
            if ((cuisine.getPrice() != sqlcui.getPrice()) &&(cuisine.getPrice() != 0) )
                sqlcui.setPrice(cuisine.getPrice());
            if (cuisine.getTag() != null )
                if (sqlcui.getTag() == null)
                    sqlcui.setTag(cuisine.getTag());
                else if (!sqlcui.getTag().equals(cuisine.getTag()))
                    sqlcui.setTag(cuisine.getTag());
            if ((cuisine.getCollect_num() != sqlcui.getCollect_num()) && (cuisine.getCollect_num()!=0) )
                sqlcui.setCollect_num(cuisine.getCollect_num());
            if (cuisine.getSell_num() != sqlcui.getSell_num() && (cuisine.getSell_num() != 0))
                sqlcui.setSell_num(cuisine.getSell_num());
        }
        if (cd.updateCuiById(sqlcui))
            return "1";
        else return "0";
    }

    @Override
    public String deleteCuiById(int id) {
        //牵一发而动全身，暂时没想好怎么写(删除了之后，收藏会怎样，订单会怎样⊙(・◇・)？)
        System.out.println("没写完");
        CuisineDao cd = new CuisineDaoImpl();

        if(cd.deleteCuiById(id))
            return "1";
        else
            return "0";
    }

    @Override
    public boolean beCollected(int id) {
        System.out.println("收藏ID = "+id);
        CuisineDao cuisineDao = new CuisineDaoImpl();
        Cuisine sqlcuisine = cuisineDao.getCuisineById(id);
        sqlcuisine.setCollect_num(sqlcuisine.getCollect_num()+1);
        if (cuisineDao.updateCuiById(sqlcuisine))
            return true;
        else
            return false;
    }

    @Override
    public boolean beSold(int id) {
        CuisineDao cuisineDao = new CuisineDaoImpl();
        Cuisine sqlcuisine = cuisineDao.getCuisineById(id);
        sqlcuisine.setSell_num(sqlcuisine.getSell_num()+1);
        if (cuisineDao.updateCuiById(sqlcuisine))
            return true;
        else
            return false;
    }
}
