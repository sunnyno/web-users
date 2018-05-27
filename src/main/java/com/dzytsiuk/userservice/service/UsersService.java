package com.dzytsiuk.userservice.service;

import com.dzytsiuk.userservice.dao.jdbc.JdbcUserDao;
import com.dzytsiuk.userservice.entities.User;

import java.util.List;

public class UsersService {
    private JdbcUserDao jdbcUserDao;

    public void setJdbcUserDao(JdbcUserDao jdbcUserDao) {
        this.jdbcUserDao = jdbcUserDao;
    }

    public List<User> getAll() {
        return jdbcUserDao.getAll();
    }

    public void insert(User user) {
        jdbcUserDao.insert(user);

    }

    public void update(User user) {
        jdbcUserDao.update(user);
    }

    public void delete(User user) {
        jdbcUserDao.delete(user);
    }

}
