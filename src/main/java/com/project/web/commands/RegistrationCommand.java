package com.project.web.commands;

import com.project.services.UserService;
import com.project.services.ValidatorService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {
    private static final Logger LOG = Logger.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String method = request.getMethod();
        if (method.equals("GET")) {
            return "registration.jsp";
        } else if (method.equals("POST")) {
            String email = request.getParameter("email");

            if (ValidatorService.validate(request)) {
                if (!UserService.isUserExist(email)) {
                    UserService.createUser(request);
                    return "login.jsp";
                }
                LOG.info("Already exist user with this email: " + email);
                request.setAttribute("errorMessage", "User already exist");
            }
            return "registration.jsp";
        }
        return "error.jsp";
    }
}
