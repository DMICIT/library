package com.project.web.commands;

import com.project.web.data.UserPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserPrincipal usersEmail = (UserPrincipal) session.getAttribute("user");
        if (usersEmail != null) {
            session.invalidate();
        }
        return "redirect:";
    }
}
