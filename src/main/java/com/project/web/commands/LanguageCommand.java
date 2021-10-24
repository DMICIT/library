package com.project.web.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String localeRequest = request.getParameter("locale");
        if (localeRequest != null) {
            HttpSession session = request.getSession();
            session.setAttribute("locale", localeRequest);
        }
        return "redirect:";
    }
}
