package com.project.services;

import com.project.dao.impl.BookDao;
import com.project.entities.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookService {
    public static List<Book> searchBook(String searchParameter) {

        List<Book> result = new ArrayList<>();

        BookDao bookDao = BookDao.getInstance();
        List<Book> allBooks = bookDao.getAll();
        for (Book book : allBooks) {
            if (book.getAuthor().toLowerCase().contains(searchParameter.toLowerCase())
                    || book.getBookName().toLowerCase().contains(searchParameter.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public static List<Book> sortBooks(List<Book> books, String sortParameter, boolean order) {
        Comparator<Book> bookComparator = getComparator(sortParameter);
        if (order) {
            books.sort(bookComparator);
        } else {
            books.sort(Collections.reverseOrder(bookComparator));
        }
        return books;
    }

    private static Comparator<Book> getComparator(String sortParameter) {
        if (sortParameter.equals("author")) {
            return Comparator.comparing(Book::getAuthor);
        } else if (sortParameter.equals("bookName")) {
            return Comparator.comparing(Book::getBookName);
        } else if (sortParameter.equals("bookEdition")) {
            return Comparator.comparing(Book::getBookEdition);
        } else if (sortParameter.equals("reliaseDate")) {
            return Comparator.comparing(Book::getReliaseDate);
        }
        return Comparator.comparing(Book::getId);
    }

    public static List<Book> getAllBooks() {
        BookDao bookDao = BookDao.getInstance();
        return bookDao.getAll();
    }
}

