package com.dzytsiuk.userservice.web.templater.servlets;

import com.dzytsiuk.userservice.entities.User;
import com.dzytsiuk.userservice.service.UsersService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteUserServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        usersService.delete(new User(id));
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("/users");
    }

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }
}
