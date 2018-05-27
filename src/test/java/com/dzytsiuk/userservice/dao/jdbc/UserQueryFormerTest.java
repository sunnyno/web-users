package com.dzytsiuk.userservice.dao.jdbc;

import com.dzytsiuk.userservice.entities.User;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;


public class UserQueryFormerTest {
    private UserQueryFormer userQueryFormer;
    private User user = new User();

    @Before
    public void setUp() {
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setAge(30);
        user.setId(1);
        userQueryFormer = new UserQueryFormer();
    }

    @Test
    public void getAllQueryTest() {
        String expected = "SELECT id, first_name, last_name, age FROM users;";
        assertEquals(expected, userQueryFormer.getAllQuery(User.class));
    }

    @Test
    public void insertQueryTest() {
        String expected = "INSERT INTO users (first_name, last_name, age) VALUES ('John', 'Doe', 30);";
        assertEquals(expected, userQueryFormer.insertQuery(user));
    }

    @Test
    public void updateQueryTest() {
        String expected = "UPDATE users SET first_name = 'John', last_name = 'Doe', age = 30 WHERE id = 1;";
        assertEquals(expected, userQueryFormer.updateQuery(user));
    }

    @Test
    public void deleteQueryTest(){
        String expected = "DELETE FROM users WHERE id = 1;";
        assertEquals(expected, userQueryFormer.deleteQuery(user));
    }
}