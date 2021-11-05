package com.project.services;

import com.project.dao.impl.BookDao;
import com.project.entities.Book;
import com.project.web.data.BookData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookService {

    public static List<Book> getAllAvailableBooks() {
        BookDao bookDao = BookDao.getInstance();
        List<Book> allbooks = bookDao.getAll();
        List<Book> allAvailableBooks = new ArrayList<>();
        for (Book book : allbooks) {
            if (CatalogService.isBookAvailable(book.getId())) {
                allAvailableBooks.add(book);
            }

        }
        return allAvailableBooks;
    }


    public static List<Book> searchBook(String searchParameter) {

        List<Book> result = new ArrayList<>();
        List<Book> allBooks = getAllAvailableBooks();
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

    public static List<BookData> getAllBooks2() {
        BookDao bookDao = BookDao.getInstance();
        List<Book> bookList = bookDao.getAll();
        List<BookData> bookDataList = new ArrayList<>();
        for (Book book : bookList) {
            BookData bookData = new BookData(book.getId(), book.getAuthor(), book.getBookName(), book.getBookEdition(), book.getReliaseDate());
            bookDataList.add(bookData);
        }
        return bookDataList;
    }

    public static Book getById(int id) {
        BookDao bookDao = BookDao.getInstance();
        Book book = bookDao.getById(id);
        return book;
    }
    public static BookData getById2(int id){
        BookDao bookDao = BookDao.getInstance();
        Book book = bookDao.getById(id);
        return new BookData(book.getId(),book.getAuthor(),book.getBookName(), book.getBookEdition(), book.getReliaseDate());

    }
    public static void create(Book book) {
        BookDao bookDao = BookDao.getInstance();
        bookDao.create(book);
    }

    public static void uodate(Book book) {
        BookDao bookDao = BookDao.getInstance();
        bookDao.update(book);
    }
}

