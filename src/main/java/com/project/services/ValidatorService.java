package com.project.services;


import javax.servlet.http.HttpServletRequest;

public class ValidatorService {
    public static boolean validate (HttpServletRequest request){
        boolean result = true;

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPass = request.getParameter("confirm_password");

        if (email.length() < 7) {
            result = false;
            request.setAttribute("error message", "False email address");
        }
        if (!password.equals(confirmPass)){
            result = false;
            request.setAttribute("error message", "Wrong password");
        }
        return result;
    }
}
