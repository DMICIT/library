package com.project.web;

import com.project.dao.impl.UserDaoImpl;
import com.project.entities.User;
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

        req.getRequestDispatcher("registration.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String sex = req.getParameter("sex");


        User user = new User(name,email,sex,phone,"user",0,password);
        UserDaoImpl userDaoImpl = UserDaoImpl.getInstance();
        userDaoImpl.create(user);

        req.getRequestDispatcher("registration.jsp").forward(req,resp);

    }
}
