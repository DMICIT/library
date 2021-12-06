package com.project.web.commands;

import com.project.entities.User;
import com.project.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserListCommand implements Command {

    private UserService userService;

    public UserListCommand() {
        this(new UserService());
    }

    public UserListCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = userService.getAllUsers();
        request.setAttribute("allUsers", users);

        return "user-list.jsp";
    }
}
