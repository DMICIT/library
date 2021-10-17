package com.project.services;

import com.project.dao.impl.UserDaoImpl;
import com.project.entities.User;
import com.project.forms.RegistrationForm;

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

    public static void createUser(RegistrationForm form) {


        String registredUser = "user";
        int banList = 0;

        UserDaoImpl instance = UserDaoImpl.getInstance();
        User user = new User(form.getName(), form.getEmail(),form.getSex(),form.getPhone(), registredUser, banList, form.getPassword());
        instance.create(user);

    }
}
