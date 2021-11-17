package com.project.web.commands;

import com.project.services.BookService;
import com.project.web.data.BookData;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminBooksCommand extends AbstractCommand{
    private static final Logger LOG = Logger.getLogger(AdminBooksCommand.class);
    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        List<BookData> books = BookService.getAllBooks();
        request.setAttribute("books",books);
        return "admin-books.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if(action.equals("delete")){
            LOG.info("Delete: " + request.getParameter("bookId"));

        }
        return "redirect:admin-books";
    }
}
