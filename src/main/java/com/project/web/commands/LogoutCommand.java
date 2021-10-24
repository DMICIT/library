package com.project.web.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String usersEmail =(String) session.getAttribute("usersEmail");
        if (usersEmail != null){
            session.invalidate();
        }
        return "index.jsp";
    }
}
