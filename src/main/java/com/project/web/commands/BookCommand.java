package com.project.web.commands;

import com.project.dao.impl.BookDao;
import com.project.entities.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class BookCommand extends AbstractCommand{

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        BookDao bookDao = BookDao.getInstance();
        List<Book> books = bookDao.getAll();
        request.setAttribute("books",books);
        return "books.jsp";

    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
