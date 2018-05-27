package com.dzytsiuk.userservice.web.templater.servlets;

import com.dzytsiuk.userservice.entities.User;
import com.dzytsiuk.userservice.service.UsersService;
import com.dzytsiuk.userservice.web.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class AddUserServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        response.getWriter().println(PageGenerator.instance().getPage("addUser.html", new HashMap<>()));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        response.setContentType("text/html;charset=utf-8");
        try {
            Integer age = Integer.parseInt(request.getParameter("age"));
            if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            } else {
                response.setStatus(HttpServletResponse.SC_OK);
                usersService.insert(new User(firstName, lastName, age));
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        response.sendRedirect("/users");
    }

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }
}
