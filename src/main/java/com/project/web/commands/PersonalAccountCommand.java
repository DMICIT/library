package com.project.web.commands;


import com.project.entities.User;
import com.project.services.UserService;
import com.project.web.data.UserPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PersonalAccountCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserPrincipal userEmail = (UserPrincipal) session.getAttribute("user");
        User user = UserService.getUserByEmail(userEmail.getEmail());
        request.setAttribute("user", user);

        return "personal-account.jsp";
    }
}
