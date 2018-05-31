package com.dzytsiuk.userservice.dao.jdbc;

import com.dzytsiuk.userservice.entity.User;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class UserRowMapperTest {

    @Test
    public void mapRowTest() {
        UserRowMapper userRowMapper = new UserRowMapper();
        ResultSet resultSetMock = mock(ResultSet.class);
        User expectedUser = new User("John", "Doe", 25);
        expectedUser.setId(1);
        try {
            Mockito.when(resultSetMock.getInt("id")).thenReturn(1);
            Mockito.when(resultSetMock.getString("first_name")).thenReturn("John");
            Mockito.when(resultSetMock.getString("last_name")).thenReturn("Doe");
            Mockito.when(resultSetMock.getInt("age")).thenReturn(25);
            assertEquals(expectedUser.toString(), userRowMapper.mapRow(resultSetMock).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}