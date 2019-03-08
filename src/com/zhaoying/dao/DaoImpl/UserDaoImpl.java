package com.zhaoying.dao.DaoImpl;

import com.zhaoying.dao.UserDao;
import com.zhaoying.instance.User;
import com.zhaoying.utils.JdbcUtils;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User getUserById(int userid) {
        String sql = "select * from user_table where id = ?";
        return (User)JdbcUtils.getObjectById(User.class,sql,userid);
    }

    @Override
    public User getUserByPhone(String phone) {
        String sql = "select * from user_table where phone = ?";
        return (User)JdbcUtils.getObjectById(User.class,sql,phone);
    }

    @Override
    public User addUser(User user) {
        String sql = "insert into user_table values(?,?,?,?,?,?,?,?,?)";
        int tag = JdbcUtils.executeSQL(sql,user.getId(),user.getUsername(),user.getPhone(),user.getAddress(),user.getSex(),user.getCtime(),user.getPasswd(),user.getCuisine_collected(),user.getRestaurant_collected());
        if (tag == 0)return null;
        else return user;
    }

    @Override
    public List<User> getAllUser() {
        String sql ="select * from user_table";
        return JdbcUtils.getList(User.class,sql);
    }

    @Override
    public int getNumOfUser() {
        String sql = "select * from user_table";
        return JdbcUtils.getListCount(sql);
    }

    @Override
    public boolean updateUserById(User user) {
        String sql ="update user_table set username = ?,phone=?,address=?,sex=?,passwd=?,cuisine_collected=?,restaurant_collected=? where id=?";
        int tag = JdbcUtils.executeSQL(sql,user.getUsername(),user.getPhone(),user.getAddress(),user.getSex(),user.getPasswd(),user.getCuisine_collected(),user.getRestaurant_collected(),user.getId());
        if (tag == 0)return false;
        else return true;
    }

    @Override
    public boolean deleteUserById(int id) {
        String sql ="delete from user_table where id = ?";
        int tag = JdbcUtils.executeSQL(sql,id);
        if (tag == 0) return false;
        else return true;
    }
}
