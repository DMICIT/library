package com.project.services;

import com.project.dao.CatalogDao;
import com.project.dao.impl.BookDaoImp;
import com.project.entities.Book;
import com.project.entities.Catalog;
import com.project.web.data.BookData;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookDaoImp bookDao;

    @Mock
    private CatalogService catalogService;
    @Mock
    Book book;



    @Test
    void testCreate() {
        when(book.getId()).thenReturn(1);
        bookService.create(book, 2);
        verify(bookDao).create(book);
        verify(catalogService).create(1,2);
    }

    @Test
    void testUpdate() {
        when(book.getId()).thenReturn(2);

        bookService.update(book, 2);
        verify(bookDao).update(book);
        verify(catalogService).update(2,2);
    }

    @Test
    void testGetBookDataById() {
        when(bookDao.getById(3)).thenReturn(book);
        when(book.getId()).thenReturn(3);
        when(book.getAuthor()).thenReturn("author");

        BookData result = bookService.getById(3);

        assertEquals(3, result.getId());
        assertEquals("author", result.getAuthor());
    }

    @Test
    void testCreateBook() {
        when(bookDao.getById(2)).thenReturn(book);
        bookService.delete(2);
        verify(bookDao).delete(book);
    }

}