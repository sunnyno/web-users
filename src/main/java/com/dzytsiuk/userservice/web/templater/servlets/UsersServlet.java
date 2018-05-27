package com.dzytsiuk.userservice.web.templater.servlets;


import com.dzytsiuk.userservice.entities.User;
import com.dzytsiuk.userservice.service.UsersService;
import com.dzytsiuk.userservice.web.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class UsersServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<User> users = usersService.getAll();
        hashMap.put("users", users);
        response.getWriter().println(PageGenerator.instance().getPage("users.html", hashMap));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }
}
