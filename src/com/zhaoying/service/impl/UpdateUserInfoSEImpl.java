package com.zhaoying.service.impl;

import com.zhaoying.dao.UserDao;
import com.zhaoying.dao.DaoImpl.UserDaoImpl;
import com.zhaoying.instance.User;
import com.zhaoying.service.UpdateUserInfoSE;

public class UpdateUserInfoSEImpl implements UpdateUserInfoSE {
    @Override
    public String setUser(User tuser) {
        UserDao ud = new UserDaoImpl();
        User sqluser  = ud.getUserById(tuser.getId());
        if (sqluser == null) return "-1";
        else {
            if (tuser.getPhone() != null)
                if (sqluser.getPhone() ==null)
                    sqluser.setPhone(tuser.getPhone());
                else if (!sqluser.getPhone().equals(tuser.getPhone()))
                    sqluser.setPhone(tuser.getPhone());
            if (tuser.getAddress() != null)
                if (sqluser.getAddress() == null)
                    sqluser.setAddress(tuser.getAddress());
                else if (!sqluser.getAddress().equals(tuser.getAddress()))
                    sqluser.setAddress(tuser.getAddress());
            if (tuser.getPasswd() != null)
                if (sqluser.getPasswd() == null)
                    sqluser.setPasswd(tuser.getPasswd());
                else  if (!sqluser.getPasswd().equals(tuser.getPasswd()))
                    sqluser.setPasswd(tuser.getPasswd());
            if (tuser.getSex() != sqluser.getSex())
                sqluser.setSex(tuser.getSex());
            if (tuser.getUsername() != null)
                if (sqluser.getUsername() == null)
                    sqluser.setUsername(tuser.getUsername());
                else if (!sqluser.getUsername().equals(tuser.getUsername()))
                    sqluser.setUsername(tuser.getUsername());
            if (ud.updateUserById(sqluser))
                return "1";
            else return "0";
        }
    }

    @Override
    public String collectCuisine(User tuser) {
        UserDao ud = new UserDaoImpl();
        User sqluser = ud.getUserById(tuser.getId());
        String old = sqluser.getCuisine_collected();
        if (old == null){
            old = tuser.getCuisine_collected();
        }
        else
            old = old + ",".trim()+tuser.getCuisine_collected();
        sqluser.setCuisine_collected(old);
        if (ud.updateUserById(sqluser)) return "1";
        else return "0";
    }

    @Override
    public String collectRestaurant(User tuser) {
        UserDao ud = new UserDaoImpl();
        User sqluser = ud.getUserById(tuser.getId());
        String old = sqluser.getRestaurant_collected();
        if (old == null){
            old = tuser.getRestaurant_collected();
        }
        else
            old = old + ",".trim()+tuser.getRestaurant_collected();
        sqluser.setRestaurant_collected(old);
        if (ud.updateUserById(sqluser)) return "1";
        else return "0";
    }

    @Override
    public String deleteUser(int id) {
        UserDao ud = new UserDaoImpl();
        if (ud.deleteUserById(id)) return "1";
        else return "0";
    }
}
