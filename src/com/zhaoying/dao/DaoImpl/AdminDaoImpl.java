package com.zhaoying.dao.DaoImpl;

import com.zhaoying.dao.AdminDao;
import com.zhaoying.instance.Admin;
import com.zhaoying.utils.JdbcUtils;

public class AdminDaoImpl implements AdminDao {
    @Override
    public Admin getAdminByPhone(String phone) {
        String sql = "select * from admin_table where phone = ?";
        return (Admin) JdbcUtils.getObjectById(Admin.class,sql,phone);
    }

    @Override
    public Admin getAdminById(int id) {
        String sql = "select * from admin_table where id = ?";
        return (Admin) JdbcUtils.getObjectById(Admin.class,sql,id);
    }
}
