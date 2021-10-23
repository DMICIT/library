package com.project.services;

import com.project.dao.impl.UserDaoImpl;
import com.project.entities.User;
import com.project.forms.RegistrationForm;

public class UserService {
    public static User getUserByEmail (String inputEmail){
        UserDaoImpl instance = UserDaoImpl.getInstance();
        return instance.getByEmail(inputEmail);

    }
    public static boolean isUserExist(String incomeEmail) {
        User userByEmail = getUserByEmail(incomeEmail);
        return userByEmail != null;
    }

    public static void createUser(RegistrationForm form) {


        String registredUser = "user";
        int banList = 0;

        UserDaoImpl instance = UserDaoImpl.getInstance();
        User user = new User(form.getName(), form.getEmail(),form.getSex(),form.getPhone(), registredUser, banList, form.getPassword());
        instance.create(user);

    }

}
