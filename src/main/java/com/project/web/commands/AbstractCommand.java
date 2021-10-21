package com.project.web.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getMethod();
        if (method.equals("GET")){
            return executeGet(request,response);

        }else if (method.equals("POST")){
            return executePost(request,response);
        }
        return "error.jsp";
    }

    protected abstract String executeGet(HttpServletRequest request, HttpServletResponse response);

    protected abstract String executePost(HttpServletRequest request, HttpServletResponse response);

}
