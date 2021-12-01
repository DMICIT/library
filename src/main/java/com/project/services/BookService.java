package com.project.services;

import com.project.dao.CatalogDao;
import com.project.dao.impl.BookDaoImp;
import com.project.dao.impl.CatalogDaoImpl;
import com.project.entities.Book;
import com.project.entities.Catalog;
import com.project.web.data.BookData;
import com.project.web.data.CatalogData;
import com.project.web.data.PaginationData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    private BookDaoImp bookDao;
    private CatalogService catalogService;

    public BookService() {
        this(BookDaoImp.getInstance(), new CatalogService());
    }

    public BookService(BookDaoImp bookDao, CatalogService catalogService) {
        this.bookDao = bookDao;
        this.catalogService = catalogService;
    }

    private List<Book> getAllAvailableBooks() {
        List<Book> allbooks = bookDao.getAll();
        List<Book> allAvailableBooks = new ArrayList<>();
        for (Book book : allbooks) {
            if (catalogService.isBookAvailable(book.getId())) {
                allAvailableBooks.add(book);
            }
        }
        return allAvailableBooks;
    }

    public PaginationData<BookData> getAllAvailableBooks(int start, int numberPerPage, String sortParameter, boolean order) {
        List<Book> allAvailableBooks = getAllAvailableBooks();
        sortBooks(allAvailableBooks, sortParameter, order);
        List<Book> paginatedList = getPaginatedBooks(allAvailableBooks, start, numberPerPage);
        List<BookData> listBookData = getBookDataList(paginatedList);
        return new PaginationData<>(listBookData, allAvailableBooks.size());
    }




    public PaginationData<BookData> searchBook(String searchParameter, int start, int numberPerPage, String sortParameter, boolean order) {

        List<Book> result = new ArrayList<>();
        List<Book> allBooks = getAllAvailableBooks();
        for (Book book : allBooks) {
            if (book.getAuthor().toLowerCase().contains(searchParameter.toLowerCase())
                    || book.getBookName().toLowerCase().contains(searchParameter.toLowerCase())) {
                result.add(book);
            }
        }
        sortBooks(result, sortParameter, order);
        List<Book> paginatedList = getPaginatedBooks(result, start, numberPerPage);
        List<BookData> listBookData = getBookDataList(paginatedList);
        return new PaginationData<>(listBookData, result.size());
    }

    private void sortBooks(List<Book> books, String sortParameter, boolean order) {
        Comparator<Book> bookComparator = getComparator(sortParameter);
        if (order) {
            books.sort(bookComparator);
        } else {
            books.sort(Collections.reverseOrder(bookComparator));
        }
    }

    private Comparator<Book> getComparator(String sortParameter) {
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


    public List<BookData> getAllBooks() {
        List<Book> bookList = bookDao.getAll();
        return getBookDataList(bookList);
    }

    public List<BookData> getSortedBooks(String sortParameter, boolean order) {
        List<Book> bookList = bookDao.getAll();
        sortBooks(bookList, sortParameter, order);
        return getBookDataList(bookList);

    }

    public BookData getById(int id) {
        Book book = bookDao.getById(id);
        return getBookData(book);
    }

    public void create(Book book, int count) {
        bookDao.create(book);
        CatalogDao catalogDao = CatalogDaoImpl.getInstance();
        Catalog catalog = new Catalog(book.getId(), count);
        catalogDao.create(catalog);
    }

    public void update(Book book, int count) {
        bookDao.update(book);
        CatalogDao catalogDao = CatalogDaoImpl.getInstance();
        Catalog catalog = catalogDao.getCatalogByBookId(book.getId());
        catalog.setCount(count);
        catalogDao.update(catalog);
    }

    public void delete(int bookId) {
        Book bookById = bookDao.getById(bookId);
        if (bookById != null) {
            bookDao.delete(bookById);
        }
    }

    private List<Book> getPaginatedBooks(List<Book> bookList, int start, int numbersPerPage) {
        List<Book> paginatedList = bookList.stream()
                .skip(start)
                .limit(numbersPerPage)
                .collect(Collectors.toList());
        return paginatedList;
    }

    private List<BookData> getBookDataList(List<Book> books) {
        List<BookData> result = new ArrayList<>();
        for (Book book : books) {
            BookData bookData = getBookData(book);
            result.add(bookData);
        }
        return result;
    }

    private BookData getBookData(Book book) {
        BookData bookData = new BookData(book.getId(), book.getAuthor(), book.getBookName(), book.getBookEdition(), book.getReliaseDate());

        CatalogDao catalogDao = CatalogDaoImpl.getInstance();
        Catalog catalogByBookId = catalogDao.getCatalogByBookId(book.getId());
        if (catalogByBookId != null) {
            CatalogData catalogData = new CatalogData();
            catalogData.setTotalQuantity(catalogByBookId.getCount());
            long checkedOutBooks = catalogService.quantityCheckedOutBooks(book.getId());
            catalogData.setCheckedOutQuantity((int) checkedOutBooks);
            catalogData.setAvailableQuantity(catalogByBookId.getCount() - (int) checkedOutBooks);
            bookData.setCatalogData(catalogData);
        }
        return bookData;
    }
}


