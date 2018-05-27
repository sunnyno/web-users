package com.dzytsiuk.userservice.dao.jdbc;

import com.dzytsiuk.userservice.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper {

    public User mapRow(ResultSet resultSet){
        //first_name, last_name, age
        User user =  new User();
        try {
            user.setId(resultSet.getInt("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setAge(resultSet.getInt("age"));
        } catch (SQLException e) {
            throw  new RuntimeException("Error mapping row ", e);
        }
        return user;
    }
}
