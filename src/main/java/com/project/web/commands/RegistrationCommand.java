package com.project.web.commands;

import com.project.forms.RegistrationForm;
import com.project.services.UserService;
import com.project.services.ValidatorService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand extends AbstractCommand {
    private static final Logger LOG = Logger.getLogger(RegistrationCommand.class);


    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        return "registration.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        RegistrationForm form = getRegistrationForm(request);

        if (ValidatorService.validate(form)){
            if (!UserService.isUserExist(form.getEmail())){
                UserService.createUser(form);
                LOG.info("User created");
                return "index.jsp";
            }else {
                LOG.info("Already exist user with this email: " + form.getEmail());
                request.setAttribute("errorMessage", "User already exist");
            }
        } LOG.info("Invalid Data");
        request.setAttribute("errorMessage", "Invalid Data");
        return "registration.jsp";

        }

    private RegistrationForm getRegistrationForm (HttpServletRequest request){
        return new RegistrationForm(request.getParameter("name"), request.getParameter("email"),request.getParameter("sex"),
                request.getParameter("phone"), request.getParameter("password"),request.getParameter("confirm_password"));
    }
}
