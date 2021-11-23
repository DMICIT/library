package com.project.web.commands;

import com.project.entities.Book;
import com.project.forms.AdminEditBookForm;
import com.project.services.BookService;
import com.project.services.ValidatorService;
import com.project.web.data.BookData;
import com.project.web.data.ValidationData;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class AdminEditBookCommand extends AbstractCommand {

    private static final Logger LOG = Logger.getLogger(AdminBooksCommand.class);
    private BookService bookService;
    private ValidatorService validatorService;

    public AdminEditBookCommand(){
        this(new BookService(), new ValidatorService());
    }

    public AdminEditBookCommand(BookService bookService, ValidatorService validatorService) {
        this.bookService = bookService;
        this.validatorService = validatorService;
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

        AdminEditBookForm form = getAdminEditBookForm(request);
         ValidationData validationData = validatorService.validate(form);
         String action = request.getParameter("action");

        if (!validationData.isValidationResult()){
            request.setAttribute("errorMessages", validationData.getErrorCodes());
            if (action.equals("edit") && form.getBookId() != null){
                BookData book = bookService.getById(Integer.parseInt(form.getBookId()));
                request.setAttribute("book", book);
                request.setAttribute("action", "edit");
            }
            return "admin-edit-book.jsp";
        }
        if (action.equals("add")) {
            Book book = new Book(form.getAuthor(), form.getBookName(), form.getBookEdition(),Date.valueOf(form.getReliaseDate()));
            bookService.create(book, Integer.parseInt(form.getCount()));

        } else if (action.equals("edit")) {
            Book book = new Book(Integer.parseInt(form.getBookId()), form.getAuthor(), form.getBookName(), form.getBookEdition(),Date.valueOf(form.getReliaseDate()));
            bookService.update(book,Integer.parseInt(form.getCount()));
        }
        return "redirect:admin-books";
    }
    private AdminEditBookForm getAdminEditBookForm(HttpServletRequest request){
        return new AdminEditBookForm(request.getParameter("bookId"),request.getParameter("author"),
                request.getParameter("bookName"),request.getParameter("bookEdition"),
                request.getParameter("reliaseDate"),request.getParameter("count"));
    }
}
