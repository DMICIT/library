package com.project.dao;

import com.project.entities.Book;
import com.project.web.data.PaginationData;

public interface BookDao extends EntityDao<Book>{

    int delete(Book book);

    PaginationData<Book> getSearchPagination(String search, int startField, int numbersPerPage, String sort, String direction);

}
