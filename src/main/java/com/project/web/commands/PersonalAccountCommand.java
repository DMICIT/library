package com.project.web.commands;


import com.project.entities.User;
import com.project.services.UserService;
import com.project.web.data.UserPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PersonalAccountCommand implements Command {

    private UserService userService;

    public PersonalAccountCommand() {
        this(new UserService());

    }
    public PersonalAccountCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserPrincipal userEmail = (UserPrincipal) session.getAttribute("user");
        User user = userService.getUserByEmail(userEmail.getEmail());
        request.setAttribute("user", user);

        return "personal-account.jsp";
    }
}
