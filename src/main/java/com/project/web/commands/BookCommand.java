package com.project.web.commands;

import com.project.services.BookService;
import com.project.web.data.BookData;
import com.project.web.data.PaginationData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BookCommand extends AbstractCommand {
    private BookService bookService;

    public BookCommand() {
        this(new BookService());
    }

    public BookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        String searchParameter = request.getParameter("search");
        String sortParameter = "id";
        String order = "ASC";
        if (request.getParameter("sort") != null) {
            sortParameter = request.getParameter("sort");
            String orderParam = request.getParameter("order");
            if (orderParam != null && orderParam.equals("desc")) {
                order = "DESC";
            }
        }

        int page = 1;
        int numberPerPage = 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        PaginationData<BookData> books;
        if (searchParameter != null) {
            books = bookService.searchBook(searchParameter, (page - 1) * numberPerPage, numberPerPage, sortParameter, order);
        } else {
            books = bookService.getAllAvailableBooks((page - 1) * numberPerPage, numberPerPage, sortParameter, order);
        }
        int totalAmount = books.getTotalAmount();
        int numberOfPages = (int) Math.ceil(totalAmount * 1.0 / numberPerPage);

        request.setAttribute("books", books.getEntityList());
        request.setAttribute("currentPage", page);
        request.setAttribute("numberOfPages", numberOfPages);
        return "books.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

}
