package com.project.dao;

import com.project.entities.Book;

public interface BookDao extends EntityDao<Book>{

    int delete(Book book);
}
