package com.project.services;

import com.project.dao.impl.UserDaoImpl;
import com.project.entities.User;

import javax.servlet.http.HttpServletRequest;

public class UserService {
    public static boolean isUserExist(String email) {

        UserDaoImpl instance = UserDaoImpl.getInstance();
        User userByEmail = instance.getByEmail(email);
        if (userByEmail != null) {
            return true;
        }
        return false;
    }

    public static void createUser(HttpServletRequest request) {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String sex = request.getParameter("sex");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String registredUser = "user";
        int banList = 0;

        UserDaoImpl instance = UserDaoImpl.getInstance();
        User user = new User(name, email, sex, phone, registredUser, banList, password);
        instance.create(user);

    }
}
