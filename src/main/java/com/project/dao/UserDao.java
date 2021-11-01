package com.project.dao;

import com.project.entities.User;

import java.util.List;

public interface UserDao extends EntityDao<User>{
    User getByEmail (String email);

    List<User> getUsersByRole (String role);

}
