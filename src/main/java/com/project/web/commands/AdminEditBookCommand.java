package com.project.web.commands;

import com.project.dao.CatalogDao;
import com.project.dao.impl.BookDao;
import com.project.entities.Book;
import com.project.services.BookService;
import com.project.web.data.BookData;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class AdminEditBookCommand extends AbstractCommand {

    private static final Logger LOG = Logger.getLogger(AdminBooksCommand.class);
    private BookService bookService;

    public AdminEditBookCommand(){
        this(new BookService());
    }

    public AdminEditBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        if (id != null) {
            int bookId = Integer.parseInt(id);
            BookData book = bookService.getById(bookId);
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
        int count = Integer.parseInt(request.getParameter("count"));

        if (action.equals("add")) {
            Book book = new Book(author, bookName, bookEdition, reliaseDate);
            bookService.create(book, count);


        } else if (action.equals("edit")) {
            Book book = new Book(Integer.parseInt(bookId), author, bookName, bookEdition, reliaseDate);
            bookService.uodate(book);
        }
        return "redirect:admin-books";
    }
}
