package com.dzytsiuk.userservice.web.servlet;


import com.dzytsiuk.userservice.entity.User;
import com.dzytsiuk.userservice.service.UserService;
import com.dzytsiuk.userservice.web.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class UsersServlet extends HttpServlet {

    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<User> users = userService.getAll();
        hashMap.put("users", users);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(PageGenerator.instance().getPage("users.html", hashMap));

    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
