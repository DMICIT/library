package com.project.web.commands;

import com.project.services.BookService;
import com.project.web.data.BookData;
import com.project.web.data.PaginationData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminBooksCommand extends AbstractCommand {

    private BookService bookService;

    public AdminBooksCommand() {
        this(new BookService());
    }

    public AdminBooksCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
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

        PaginationData<BookData> books = bookService.getAllAvailableBooks((page - 1) * numberPerPage, numberPerPage, sortParameter, order);
        int totalAmount = books.getTotalAmount();
        int numberOfPages = (int) Math.ceil(totalAmount * 1.0 / numberPerPage);

        request.setAttribute("books", books.getEntityList());
        request.setAttribute("currentPage", page);
        request.setAttribute("numberOfPages", numberOfPages);
        return "admin-books.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action.equals("delete")) {
            bookService.delete(Integer.parseInt(request.getParameter("bookId")));
        }
        return "redirect:admin-books";
    }
}
