package com.project.dao;

import java.util.List;

public interface EntityDao<T> {

    List<T> getAll();
    T getById(int id);
    int create (T entity);
    int update (T entity);

}
