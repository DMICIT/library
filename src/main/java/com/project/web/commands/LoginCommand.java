package com.project.web.commands;

import com.project.dao.impl.UserDaoImpl;
import com.project.entities.User;
import com.project.forms.LoginForm;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand extends AbstractCommand {
    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        return "login.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDaoImpl instance = UserDaoImpl.getInstance();
        User user = instance.getByEmail(email);
        if (user == null) {
            request.setAttribute("errorMessage", "User not found, please register");
            return "registration.jsp";
        }
        if (!user.getPassword().equals(password)) {
            request.setAttribute("errorMessage", "Wrong password!");
            return "login.jsp";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", email);

        return "index.jsp";
    }

    private LoginForm getLoginForm(HttpServletRequest request) {
        return new LoginForm(request.getParameter("email"), request.getParameter("password")
        );
    }
}
