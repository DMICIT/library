package com.project.web.commands;

import com.project.services.BookService;
import com.project.services.UserService;
import com.project.web.data.BookData;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;



public class AdminBooksCommand extends AbstractCommand{

    private static final Logger LOG = Logger.getLogger(AdminBooksCommand.class);
    private BookService bookService;

    public AdminBooksCommand(){
        this(new BookService());
    }

    public AdminBooksCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        List<BookData> books = bookService.getAllBooks();
        request.setAttribute("books",books);
        return "admin-books.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if(action.equals("delete")){

            bookService.delete(Integer.parseInt(request.getParameter("bookId")));

        }
        return "redirect:admin-books";
    }
}
