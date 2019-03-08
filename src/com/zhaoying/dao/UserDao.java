package com.zhaoying.dao;

import com.zhaoying.instance.User;

import java.util.List;

public interface UserDao {

    User getUserById(int userid);

    User getUserByPhone(String phone);

    User addUser(User user);

    List<User> getAllUser();

    int getNumOfUser();

    boolean updateUserById(User user);

    boolean deleteUserById(int id);
}
