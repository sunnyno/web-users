package com.dzytsiuk.userservice.main;

import com.dzytsiuk.userservice.dao.jdbc.JdbcUserDao;
import com.dzytsiuk.userservice.service.UsersService;
import com.dzytsiuk.userservice.web.templater.servlets.DeleteUserServlet;
import com.dzytsiuk.userservice.web.templater.servlets.EditUserServlet;
import com.dzytsiuk.userservice.web.templater.servlets.UsersServlet;
import com.dzytsiuk.userservice.web.templater.servlets.AddUserServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        UsersService usersService = new UsersService();
        usersService.setJdbcUserDao(jdbcUserDao);

        UsersServlet usersServlet = new UsersServlet();
        usersServlet.setUsersService(usersService);
        AddUserServlet addUserServlet = new AddUserServlet();
        addUserServlet.setUsersService(usersService);
        EditUserServlet editUserServlet = new EditUserServlet();
        editUserServlet.setUsersService(usersService);
        DeleteUserServlet deleteUserServlet = new DeleteUserServlet();
        deleteUserServlet.setUsersService(usersService);


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
