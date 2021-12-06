package com.project.services;

import com.project.dao.impl.BookDaoImp;
import com.project.entities.Book;
import com.project.web.data.BookData;
import com.project.web.data.PaginationData;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private BookDaoImp bookDao;
    private CatalogService catalogService;

    public BookService(BookDaoImp bookDao, CatalogService catalogService) {
        this.bookDao = bookDao;
        this.catalogService = catalogService;
    }

    public BookService() {
        this(BookDaoImp.getInstance(), new CatalogService());
    }

    public PaginationData<BookData> getAllAvailableBooks(int start, int numberPerPage, String sortParameter, String order) {
        PaginationData<Book> allAvailableBooks = getPaginationBooks(start, numberPerPage, sortParameter, order);
        List<Book> entityList = allAvailableBooks.getEntityList();
        int totalAmount = allAvailableBooks.getTotalAmount();
        List<BookData> listBookData = getBookDataList(entityList);

        return new PaginationData<>(listBookData, totalAmount);
    }

    private PaginationData<Book> getPaginationBooks(int start, int numberPerPage, String sortParameter, String order) {
        return bookDao.getPagination(start, numberPerPage, sortParameter, order);
    }

    public PaginationData<BookData> searchBook(String searchParameter, int start, int numberPerPage, String sortParameter, String order) {
        PaginationData<Book> allAvailableBooks = bookDao.getSearchPagination(searchParameter, start, numberPerPage, sortParameter, order);
        List<Book> entityList = allAvailableBooks.getEntityList();
        int totalAmount = allAvailableBooks.getTotalAmount();
        List<BookData> listBookData = getBookDataList(entityList);

        return new PaginationData<>(listBookData, totalAmount);
    }

    private BookData getBookData(Book book) {
        BookData bookData = new BookData(book.getId(), book.getAuthor(), book.getBookName(), book.getBookEdition(), book.getReliaseDate());
        bookData.setCatalogData(catalogService.getCatalogData(book.getId()));
        return bookData;
    }

    private List<BookData> getBookDataList(List<Book> books) {
        List<BookData> result = new ArrayList<>();
        for (Book book : books) {
            BookData bookData = getBookData(book);
            result.add(bookData);
        }
        return result;
    }


    public BookData getById(int id) {
        Book book = bookDao.getById(id);
        return getBookData(book);
    }

    public void create(Book book, int count) {
        bookDao.create(book);
        catalogService.create(book.getId(), count);
    }

    public void update(Book book, int count) {
        bookDao.update(book);
        catalogService.update(book.getId(), count);
    }

    public void delete(int bookId) {
        Book bookById = bookDao.getById(bookId);
        if (bookById != null) {
            bookDao.delete(bookById);
        }
    }
}


