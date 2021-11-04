package com.project.web.commands;

import com.project.entities.User;
import com.project.services.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserListCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = UserService.getAllUsers();
        request.setAttribute("allUsers", users);

        return "user-list.jsp";    }
}
