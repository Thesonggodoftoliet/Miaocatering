package com.zhaoying.dao.DaoImpl;

import com.zhaoying.dao.CuisineDao;
import com.zhaoying.instance.Cuisine;
import com.zhaoying.utils.JdbcUtils;

import java.util.List;

public class CuisineDaoImpl implements CuisineDao {
    @Override
    public List<Cuisine> getAllCuisine(String exasql) {
        String sql = "select * from cuisine_table "+exasql;
        return JdbcUtils.getList(Cuisine.class,sql);
    }

    @Override
    public List<Cuisine> getCuisineByResId(int id) {
        String sql ="select * from cuisine_table where restau_id = "+id;
        return JdbcUtils.getList(Cuisine.class,sql);
    }

    @Override
    public Cuisine getCuisineById(int id) {
        String sql = "select * from cuisine_table where id = ?";
        return (Cuisine)JdbcUtils.getObjectById(Cuisine.class,sql,id);
    }

    @Override
    public Cuisine addCuisine(Cuisine cuisine) {
        String sql = "insert into cuisine_table values(?,?,?,?,?,?,?,?,?,?,?)";
        int tag = JdbcUtils.executeSQL(sql,cuisine.getId(),cuisine.getC_name(),cuisine.getPrice(),cuisine.getCover_url(),cuisine.getDetail_url(),cuisine.getOrigin_price(),cuisine.getSell_num(),cuisine.getCollect_num(),cuisine.getRestau_id(),cuisine.getCtime(),cuisine.getTag());
        if (tag == 0)return null;
        else return cuisine;
    }

    @Override
    public boolean updateCuiById(Cuisine cuisine) {
        String sql = "update cuisine_table set c_name=?,price=?,cover_url=?,detail_url=?,origin_price=?,sell_num=?,collect_num=?,tag=? where id=?";
        int tag = JdbcUtils.executeSQL(sql,cuisine.getC_name(),cuisine.getPrice(),cuisine.getCover_url(),cuisine.getDetail_url(),cuisine.getOrigin_price(),cuisine.getSell_num(),cuisine.getCollect_num(),cuisine.getTag(),cuisine.getId());
        if (tag == 0)return false;
        else return true;
    }

    @Override
    public int getNumOfCuisine() {
        String sql = "select * from cuisine_table";
        return JdbcUtils.getListCount(sql);
    }

    @Override
    public boolean deleteCuiById(int id) {
        String sql = "delete from cuisine_table where id= ?";
        int tag = JdbcUtils.executeSQL(sql,id);
        if (tag == 0)return false;
        else return true;
    }
}
