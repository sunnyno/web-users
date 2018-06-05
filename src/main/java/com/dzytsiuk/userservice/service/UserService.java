package com.dzytsiuk.userservice.service;

import com.dzytsiuk.userservice.dao.UserDao;
import com.dzytsiuk.userservice.dao.jdbc.JdbcUserDao;
import com.dzytsiuk.userservice.entity.User;

import java.util.List;

public class UserService {
    private UserDao userDao;

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User getById(long id) {
        return userDao.getById(id);
    }

    public void insert(User user) {
        userDao.insert(user);

    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(User user) {
        userDao.delete(user);
    }

    public void setUserDao(JdbcUserDao userDao) {
        this.userDao = userDao;
    }


}
