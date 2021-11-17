package com.project.web.commands;

import com.project.services.BookService;
import com.project.web.data.BookData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BookCommand extends AbstractCommand {
    private BookService bookService;

    public BookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        String searchParameter = request.getParameter("search");
        String sortParameter = request.getParameter("sort");
        List<BookData> books;
        if (searchParameter != null) {
            books = BookService.searchBook(searchParameter);
        } else {
            books = bookService.getAllAvailableBooks2();
        }
        if (sortParameter != null) {
            // ascending asc
            // descending desc
            String orderParam = request.getParameter("order");
            boolean order = true;
            if (orderParam != null && orderParam.equals("desc")){
                order = false;
            }
            BookService.sortBooks(books, sortParameter, order);
        }
        request.setAttribute("books", books);
        return "books.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

}
