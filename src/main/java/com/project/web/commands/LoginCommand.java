package com.project.web.commands;

import com.project.entities.User;
import com.project.forms.LoginForm;
import com.project.services.PenaltyService;
import com.project.services.UserService;
import com.project.services.ValidatorService;
import com.project.web.data.UserPrincipal;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand extends AbstractCommand {

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);
    private UserService userService;
    private PenaltyService penaltyService;

    public LoginCommand(){
        this(new UserService(), new PenaltyService());
    }

    public LoginCommand(UserService userService, PenaltyService penaltyService) {
        this.userService = userService;
        this.penaltyService = penaltyService;
    }

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        return "login.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {

        LoginForm form = getLoginForm(request);
        User user = userService.getUserByEmail(form.getEmail());

        if (!ValidatorService.validate(form)) {
            request.setAttribute("errorMessage", "Not valid Data");
            return "login.jsp";
        }
        if (user == null) {
            request.setAttribute("errorMessage", "User not found, please register");
            return "redirect:registration";
        }
        if (!user.getPassword().equals(form.getPassword())) {
            request.setAttribute("errorMessage", "Wrong password!");
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
