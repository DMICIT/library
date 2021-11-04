package com.project.services;

import com.project.dao.CatalogDao;
import com.project.dao.OrderDao;
import com.project.dao.impl.CatalogDaoImpl;
import com.project.dao.impl.OrderDaoImpl;
import com.project.entities.Catalog;
import com.project.entities.Order;

import java.util.List;

public class CatalogService {

    public static boolean isBookAvailable(int bookId) {
        CatalogDao catalogDao = CatalogDaoImpl.getInstance();
        Catalog catalog = catalogDao.getCatalogByBookId(bookId);

        OrderDao orderDao = OrderDaoImpl.getInstance();
        List<Order> allOrdersByBook = orderDao.getAllOrdersByBook(bookId);

        long checkedOutBooks = allOrdersByBook.stream()
                .filter(order -> order.getStatus().equals("checked out"))
                .count();
        return catalog != null && catalog.getCount() > checkedOutBooks;
    }
}