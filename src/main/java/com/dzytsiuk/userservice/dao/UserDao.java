package com.dzytsiuk.userservice.dao;

import com.dzytsiuk.userservice.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    void insert(User user);

    void update(User user);

    void delete(User user);
}
