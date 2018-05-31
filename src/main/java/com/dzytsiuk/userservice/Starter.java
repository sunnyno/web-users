package com.dzytsiuk.userservice;

import com.dzytsiuk.userservice.dao.jdbc.JdbcUserDao;
import com.dzytsiuk.userservice.service.UserService;
import com.dzytsiuk.userservice.web.servlet.DeleteUserServlet;
import com.dzytsiuk.userservice.web.servlet.EditUserServlet;
import com.dzytsiuk.userservice.web.servlet.UsersServlet;
import com.dzytsiuk.userservice.web.servlet.AddUserServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Starter {
    public static void main(String[] args) throws Exception {
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        UserService userService = new UserService();
        userService.setUserDao(jdbcUserDao);

        UsersServlet usersServlet = new UsersServlet();
        usersServlet.setUserService(userService);
        AddUserServlet addUserServlet = new AddUserServlet();
        addUserServlet.setUserService(userService);
        EditUserServlet editUserServlet = new EditUserServlet();
        editUserServlet.setUserService(userService);
        DeleteUserServlet deleteUserServlet = new DeleteUserServlet();
        deleteUserServlet.setUserService(userService);


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(usersServlet), "/users");
        context.addServlet(new ServletHolder(addUserServlet), "/user/add");
        context.addServlet(new ServletHolder(editUserServlet), "/user/edit");
        context.addServlet(new ServletHolder(deleteUserServlet), "/user/delete");


        Server server = new Server(3000);
        server.setHandler(context);

        server.start();

    }
}
