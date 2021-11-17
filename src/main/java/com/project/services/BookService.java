package com.project.services;

import com.project.dao.CatalogDao;
import com.project.dao.impl.BookDao;
import com.project.dao.impl.CatalogDaoImpl;
import com.project.entities.Book;
import com.project.entities.Catalog;
import com.project.web.data.BookData;
import com.project.web.data.CatalogData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookService {
    private BookDao bookDao;
    private CatalogService catalogService;

    public BookService(){
        this(BookDao.getInstance(),new CatalogService());
    }

    public BookService(BookDao bookDao, CatalogService catalogService) {
        this.bookDao = bookDao;
        this.catalogService = catalogService;
    }


    public List<BookData> getAllAvailableBooks() {
        List<Book> allbooks = bookDao.getAll();
        List<Book> allAvailableBooks = new ArrayList<>();
        for (Book book : allbooks) {
            if (catalogService.isBookAvailable(book.getId())) {
                allAvailableBooks.add(book);
            }
        }

        return getBookDataList(allAvailableBooks);
    }


    private List<BookData> getBookDataList(List<Book> books) {
        List<BookData> result = new ArrayList<>();
        for (Book book : books) {
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

            result.add(bookData);
        }
        return result;
    }


    public List<BookData> searchBook(String searchParameter) {

        List<BookData> result = new ArrayList<>();
        List<BookData> allBooks = getAllAvailableBooks();
        for (BookData book : allBooks) {
            if (book.getAuthor().toLowerCase().contains(searchParameter.toLowerCase())
                    || book.getBookName().toLowerCase().contains(searchParameter.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public void sortBooks(List<BookData> books, String sortParameter, boolean order) {
        Comparator<BookData> bookComparator = getComparator(sortParameter);
        if (order) {
            books.sort(bookComparator);
        } else {
            books.sort(Collections.reverseOrder(bookComparator));
        }
    }

    private Comparator<BookData> getComparator(String sortParameter) {
        if (sortParameter.equals("author")) {
            return Comparator.comparing(BookData::getAuthor);
        } else if (sortParameter.equals("bookName")) {
            return Comparator.comparing(BookData::getBookName);
        } else if (sortParameter.equals("bookEdition")) {
            return Comparator.comparing(BookData::getBookEdition);
        } else if (sortParameter.equals("reliaseDate")) {
            return Comparator.comparing(BookData::getReliaseDate);
        }
        return Comparator.comparing(BookData::getId);
    }


    public List<BookData> getAllBooks() {
        BookDao bookDao = BookDao.getInstance();
        List<Book> bookList = bookDao.getAll();
        return getBookDataList(bookList);
    }

    public BookData getById(int id) {
        BookDao bookDao = BookDao.getInstance();
        Book book = bookDao.getById(id);
        return new BookData(book.getId(), book.getAuthor(), book.getBookName(), book.getBookEdition(), book.getReliaseDate());

    }

    public void create(Book book, int count) {
        BookDao bookDao = BookDao.getInstance();
        bookDao.create(book);
        CatalogDao catalogDao = CatalogDaoImpl.getInstance();
        Catalog catalog = new Catalog(book.getId(), count);
        catalogDao.create(catalog);
    }

    public void uodate(Book book) {
        BookDao bookDao = BookDao.getInstance();
        bookDao.update(book);
    }
}


