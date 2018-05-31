package com.dzytsiuk.userservice.dao.jdbc;

import com.dzytsiuk.userservice.dao.UserDao;
import com.dzytsiuk.userservice.dao.jdbc.settings.DBConnection;
import com.dzytsiuk.userservice.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {
    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();

    private UserQueryFormer userQueryFormer = new UserQueryFormer();
    private Connection connection = DBConnection.connectToDB();

    @Override
    public List<User> getAll() {
        String getAllQuery = userQueryFormer.getAllQuery(User.class);
        try (PreparedStatement statement = connection.prepareStatement(getAllQuery);
             ResultSet resultSet = statement.executeQuery();) {

            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                users.add(USER_ROW_MAPPER.mapRow(resultSet));
            }

            return users;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting users ", e);
        }
    }

    @Override
    public void insert(User user) {
        String insertQuery = userQueryFormer.insertQuery(user);
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user "+user, e);
        }

    }

    @Override
    public void update(User user) {
        String updateQuery = userQueryFormer.updateQuery(user);
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user "+user, e);
        }
    }

    @Override
    public void delete(User user) {
        String deleteQuery = userQueryFormer.deleteQuery(user);
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user "+user, e);
        }

    }

}
