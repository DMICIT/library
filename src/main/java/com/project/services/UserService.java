package com.project.services;

import com.project.dao.impl.UserDaoImpl;
import com.project.entities.User;
import com.project.forms.RegistrationForm;
import org.apache.log4j.Logger;

import java.util.List;

public class UserService {

    private static final Logger LOG = Logger.getLogger(UserService.class);

    private UserDaoImpl userDao;

    public UserService(){
        this(UserDaoImpl.getInstance());
    }
    public UserService(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public User getUserByEmail(String inputEmail) {
        return userDao.getByEmail(inputEmail);

    }

    public boolean isUserExist(String incomeEmail) {
        User userByEmail = getUserByEmail(incomeEmail);
        return userByEmail != null;
    }
    public void createUser(User user) {
        UserDaoImpl instance = UserDaoImpl.getInstance();
        instance.create(user);

    }

    public void createUser(RegistrationForm form) {

        String registredUser = "user";
        int banList = 0;

        UserDaoImpl instance = UserDaoImpl.getInstance();
        User user = new User(form.getName(), form.getEmail(), form.getSex(), form.getPhone(), registredUser, banList, form.getPassword());
        LOG.info("User : " + user);
        int result = instance.create(user);
        LOG.info("result is :" + result);
    }

    public List<User> getAllLibrarians() {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        return userDao.getUsersByRole("librarian");
    }

    public List<User> getAllUsers() {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        return userDao.getUsersByRole("user");
    }

    public void banUser(int userId, boolean value) {

        UserDaoImpl userDao = UserDaoImpl.getInstance();
        User user = userDao.getById(userId);
        if (value) {
            user.setBanList(1);
        } else {
            user.setBanList(0);
        }
        userDao.update(user);
    }

    public void deleteUser(int userId){

        UserDaoImpl userDao = UserDaoImpl.getInstance();
        userDao.deleteUser(userId);
    }

}
