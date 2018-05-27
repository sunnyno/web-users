package com.dzytsiuk.userservice.dao.jdbc;

import com.dzytsiuk.userservice.dao.jdbc.settings.DBConnection;
import com.dzytsiuk.userservice.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao {
    private UserQueryFormer userQueryFormer = new UserQueryFormer();
    private Connection connection = DBConnection.connectToDB();

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String getAllQuery = userQueryFormer.getAllQuery(User.class);
        try (PreparedStatement statement = connection.prepareStatement(getAllQuery);) {
            UserRowMapper userRowMapper = new UserRowMapper();
            try (ResultSet resultSet = statement.executeQuery(getAllQuery);) {
                while (resultSet.next()) {
                    users.add(userRowMapper.mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting users ", e);
        }
        return users;
    }

    public void insert(Object object) {
        String insertQuery = userQueryFormer.insertQuery(object);
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.executeUpdate(insertQuery);
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user ", e);
        }

    }

    public void update(Object object) {
        String updateQuery = userQueryFormer.updateQuery(object);
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.executeUpdate(updateQuery);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user ", e);
        }
    }

    public void delete(Object object){
        String deleteQuery = userQueryFormer.deleteQuery(object);
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user ", e);
        }
    }

}
