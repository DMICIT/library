package com.project.dao;

import com.project.web.data.PaginationData;

public interface PaginationDao<T>{

    PaginationData<T> getPagination (int startField, int numbersPerPage, String sort, String direction);

}

