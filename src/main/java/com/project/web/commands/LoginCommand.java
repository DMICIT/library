package com.project.web.commands;

import com.project.entities.User;
import com.project.forms.LoginForm;
import com.project.services.PenaltyService;
import com.project.services.UserService;
import com.project.services.ValidatorService;
import com.project.web.data.UserPrincipal;
import com.project.web.data.ValidationData;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;

public class LoginCommand extends AbstractCommand {

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);
    private UserService userService;
    private PenaltyService penaltyService;
    private ValidatorService validatorService;

    public LoginCommand(UserService userService, PenaltyService penaltyService, ValidatorService validatorService) {
        this.userService = userService;
        this.penaltyService = penaltyService;
        this.validatorService = validatorService;
    }

    public LoginCommand(){
        this(new UserService(), new PenaltyService(), new ValidatorService());
    }



    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        return "login.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {

        LoginForm form = getLoginForm(request);
        User user = userService.getUserByEmail(form.getEmail());
        ValidationData validationData = validatorService.validate(form);

        if (!validationData.isValidationResult()) {
            request.setAttribute("errorMessages", validationData.getErrorCodes());
            return "login.jsp";
        }
        if (user == null) {
            request.setAttribute("errorMessages", "User not found, please register");
            return "redirect:registration";
        }
        if (!user.getPassword().equals(form.getPassword())) {
            request.setAttribute("errorMessages", Collections.singletonList("error.wrong.password"));
            return "login.jsp";
        }
        if (user.getBanList() == 1){
            return "ban-page.jsp";
        }

        HttpSession session = request.getSession();
        UserPrincipal userPrincipal = new UserPrincipal(user.getEmail(), user.getRole());
        session.setAttribute("user", userPrincipal);

        penaltyService.checkUserPenalty(user.getId());

        return "redirect:";
    }

    private LoginForm getLoginForm(HttpServletRequest request) {
        return new LoginForm(request.getParameter("email"), request.getParameter("password"));
    }
}
