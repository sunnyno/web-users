package com.dzytsiuk.userservice.web.templater.servlets;

import com.dzytsiuk.userservice.entities.User;
import com.dzytsiuk.userservice.service.UsersService;
import com.dzytsiuk.userservice.web.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class EditUserServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HashMap<String, Object> parametersMap = new HashMap<>();
        long id = Integer.parseInt(request.getParameter("id"));
        parametersMap.put("id", id);
        response.getWriter().println(PageGenerator.instance().getPage("editUser.html", parametersMap));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        User user = new User();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        response.setContentType("text/html;charset=utf-8");
        try {
            int age = Integer.parseInt(request.getParameter("age"));
            if (firstName != null && lastName != null) {
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setAge(age);
                user.setId(id);
                usersService.update(user);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
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
