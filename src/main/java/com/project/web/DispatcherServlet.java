package com.project.web;

import com.project.dao.impl.UserDaoImpl;
import com.project.entities.User;
import com.project.services.UserService;
import com.project.services.ValidatorService;
import com.project.web.commands.LoginCommand;
import com.project.web.commands.RegistrationCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    private final static Logger LOG = Logger.getLogger(DispatcherServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        int lastSymbol = requestUri.lastIndexOf('/');
        String path = requestUri.substring(lastSymbol + 1);
        if (path.equals("registration")) {
            String registrationPage = new RegistrationCommand().execute(request, response);
            request.getRequestDispatcher("/WEB-INF/pages/" + registrationPage).forward(request, response);
        } else if (path.equals("login")) {
            String loginPage = new LoginCommand().execute(request, response);
            request.getRequestDispatcher("/WEB-INF/pages/" + loginPage).forward(request, response);

        }
    }
}
