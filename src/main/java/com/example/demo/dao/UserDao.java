package com.example.demo.dao;

import com.example.demo.entity.User;


import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void add(User user);

    User getSingleUser(int id);

    void deleteUser(int id);

    void updateUser(int id, User user);
}
