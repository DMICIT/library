package com.project.dao;

import com.project.entities.User;

public interface UserDao extends EntityDao<User>{
    User getByEmail (String email);
}
