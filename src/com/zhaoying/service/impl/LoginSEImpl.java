package com.zhaoying.service.impl;

import com.zhaoying.dao.AdminDao;
import com.zhaoying.dao.RestaurantDao;
import com.zhaoying.dao.UserDao;
import com.zhaoying.dao.DaoImpl.AdminDaoImpl;
import com.zhaoying.dao.DaoImpl.RestaurantDaoImpl;
import com.zhaoying.dao.DaoImpl.UserDaoImpl;
import com.zhaoying.instance.Admin;
import com.zhaoying.instance.Restaurant;
import com.zhaoying.instance.User;
import com.zhaoying.service.LoginSE;

public class LoginSEImpl implements LoginSE {
    @Override
    public String userCheck(User tuser) {
        // 1 登陆成功 0密码错误 -1账号无效
        User sqluser;
        UserDao ud = new UserDaoImpl();
        if (tuser.getPhone()!=null)
            sqluser = ud.getUserByPhone(tuser.getPhone());
        else
            sqluser = ud.getUserById(tuser.getId());

        if (sqluser == null) return "-1";
        else{
            if(sqluser.getPasswd().equals(tuser.getPasswd())) return Integer.toString(sqluser.getId());

            else return "0";
        }
    }

    @Override
    public String adminCheck(Admin admin) {
        Admin sqladmin = null;
        AdminDao ad = new AdminDaoImpl();
        sqladmin = ad.getAdminByPhone(admin.getPhone());

        if (sqladmin == null) return "-1";
        else {
            if (sqladmin.getPasswd().equals(admin.getPasswd())) return Integer.toString(sqladmin.getId());

            else return "0";
        }
    }

    @Override
    public String retaurantCheck(Restaurant restaurant) {
        Restaurant sqlrestaurant;
        RestaurantDao rd = new RestaurantDaoImpl();
        sqlrestaurant = rd.getResByPhone(restaurant.getPhone());

        if (sqlrestaurant == null) return "-1";
        else {
            if (sqlrestaurant.getPasswd().equals(restaurant.getPasswd())) return Integer.toString(sqlrestaurant.getId());
            else  return "0";
        }
    }
}
