package com.zhaoying.dao.DaoImpl;

import com.zhaoying.dao.SwiperDao;
import com.zhaoying.instance.Swiper;
import com.zhaoying.utils.JdbcUtils;

import java.util.List;

public class SwiperDaoImpl implements SwiperDao {
    @Override
    public Swiper getSwiperByIndex(int index) {
        String sql = "select * from swiper_table where position = ?";
        return (Swiper)JdbcUtils.getObjectById(Swiper.class,sql,index);
    }

    @Override
    public List<Swiper> getAllSwiper() {
        String sql = "select * from swiper_table";
        return JdbcUtils.getList(Swiper.class,sql);
    }

    @Override
    public Swiper addSwiper(Swiper swiper) {
        String sql = "insert into swiper_table values(?,?,?,?)";
        int tag = JdbcUtils.executeSQL(sql,swiper.getId(),swiper.getUrl(),swiper.getDescription(),swiper.getPosition());
        if (tag == 0)
            return null;
        else
            return swiper;
    }

    @Override
    public boolean updateSwiByIndex(Swiper swiper) {
        String sql = "update swiper_table set url=?,description=?,id=? where position=?";
        int tag = JdbcUtils.executeSQL(sql,swiper.getUrl(),swiper.getDescription(),swiper.getId(),swiper.getPosition());
        if (tag == 0)
            return false;
        else
            return true;
    }

    @Override
    public boolean deleteSwiById(int id) {
        String sql = "delete from swiper_table where id = ?";
        int tag = JdbcUtils.executeSQL(sql,id);
        if (tag == 0)
            return false;
        else
            return true;
    }

    @Override
    public int getNumOfSwiper() {
        String sql = "select * from swiper_table";
        return JdbcUtils.getListCount(sql);
    }
}
