package com.project.web.commands;

import com.project.dao.impl.BookDao;
import com.project.entities.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class AdminEditBookCommand extends AbstractCommand {
    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        if (id != null) {
            int bookId = Integer.parseInt(id);
            BookDao bookDao = BookDao.getInstance();
            Book book = bookDao.getById(bookId);
            request.setAttribute("book", book);
            request.setAttribute("action", "edit");
        }
        return "admin-edit-book.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {

        String action = request.getParameter("action");
        String bookId = request.getParameter("bookId");
        String author = request.getParameter("author");
        String bookName = request.getParameter("bookName");
        String bookEdition = request.getParameter("bookEdition");
        Date reliaseDate = Date.valueOf(request.getParameter("reliaseDate"));
        if (action.equals("add")) {
            Book book = new Book(author, bookName, bookEdition, reliaseDate);
            BookDao bookDao = BookDao.getInstance();
            bookDao.create(book);
        } else if (action.equals("edit")) {
            Book book = new Book(Integer.parseInt(bookId), author, bookName, bookEdition, reliaseDate);
            BookDao bookDao = BookDao.getInstance();
            bookDao.update(book);
        }
        return "redirect:admin-books";
    }
}
