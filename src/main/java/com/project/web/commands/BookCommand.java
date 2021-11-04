package com.project.web.commands;

import com.project.dao.impl.BookDao;
import com.project.entities.Book;
import com.project.services.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BookCommand extends AbstractCommand {

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        String searchParameter = request.getParameter("search");
        String sortParameter = request.getParameter("sort");
        List<Book> books;
        if (searchParameter != null) {
            books = BookService.searchBook(searchParameter);
        } else {
            books = BookService.getAllAvailableBooks();
        }
        if (sortParameter != null) {
            // ascending asc
            // descending desc
            String orderParam = request.getParameter("order");
            boolean order = true;
            if (orderParam != null && orderParam.equals("desc")){
                order = false;
            }
            books = BookService.sortBooks(books,sortParameter, order);
        }
        request.setAttribute("books", books);
        return "books.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
