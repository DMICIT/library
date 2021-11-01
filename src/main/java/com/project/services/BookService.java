package com.project.services;

import com.project.dao.impl.BookDao;
import com.project.entities.Book;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    public static List<Book> searchBook(String searchParameter) {

        List<Book> result = new ArrayList<>();

        BookDao bookDao = BookDao.getInstance();
        List<Book> allBooks = bookDao.getAll();
        for (int i = 0; i < allBooks.size(); i++) {
            Book book = allBooks.get(i);
            if (book.getAuthor().toLowerCase().contains(searchParameter.toLowerCase())
                    || book.getBookName().toLowerCase().contains(searchParameter.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }
}
