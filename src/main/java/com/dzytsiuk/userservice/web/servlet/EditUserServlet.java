package com.dzytsiuk.userservice.web.servlet;

import com.dzytsiuk.userservice.entity.User;
import com.dzytsiuk.userservice.service.UserService;
import com.dzytsiuk.userservice.web.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class EditUserServlet extends HttpServlet {

    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HashMap<String, Object> parametersMap = new HashMap<>();
        long id = Long.parseLong(request.getParameter("id"));
        parametersMap.put("id", id);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(PageGenerator.instance().getPage("editUser.html", parametersMap));

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
                userService.update(user);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        response.sendRedirect("/users");
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
