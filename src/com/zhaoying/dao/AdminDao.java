package com.zhaoying.dao;

import com.zhaoying.instance.Admin;

public interface AdminDao {
    Admin getAdminByPhone(String phone);
    Admin getAdminById(int id);
}
