package com.project.services;

import com.project.dao.CatalogDao;
import com.project.dao.OrderDao;
import com.project.dao.PenaltyDao;
import com.project.dao.impl.CatalogDaoImpl;
import com.project.dao.impl.OrderDaoImpl;
import com.project.dao.impl.PenaltyDaoImpl;
import com.project.entities.Catalog;
import com.project.entities.Order;
import com.project.entities.Penalty;
import com.project.web.data.BookData;
import com.project.web.data.OrderData;
import com.project.web.data.PenaltyData;
import com.project.web.data.UserData;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private OrderDao orderDao;
    private BookService bookService;
    private UserService userService;
    private CatalogDao catalogDao;
    private PenaltyDao penaltyDao;

    public OrderService(OrderDao orderDao, BookService bookService, UserService userService, CatalogDao catalogDao, PenaltyDao penaltyDao) {
        this.orderDao = orderDao;
        this.bookService = bookService;
        this.userService = userService;
        this.catalogDao = catalogDao;
        this.penaltyDao = penaltyDao;
    }

    public OrderService() {
        this(OrderDaoImpl.getInstance(), new BookService(), new UserService(), CatalogDaoImpl.getInstance(), PenaltyDaoImpl.getInstance());
    }


    public void changeStatus(int orderId, String status) {
        Order order = orderDao.getById(orderId);
        order.setStatus(status);
        if (status.equals("returned")) {
            Catalog catalogByBookId = catalogDao.getCatalogByBookId(order.getBookId());
            int count = catalogByBookId.getCount() + 1;
            catalogByBookId.setCount(count);
            catalogDao.update(catalogByBookId);
        }
        orderDao.update(order);
    }

    public List<OrderData> getAllOrdersByUser(int userId) {
        List<Order> orders = orderDao.getAllOrdersByUser(userId);
        return getOrderDataList(orders);
    }

    public void create(Order order) {
        Catalog catalogByBookId = catalogDao.getCatalogByBookId(order.getBookId());
        int count = catalogByBookId.getCount() - 1;
        catalogByBookId.setCount(count);
        catalogDao.update(catalogByBookId);
        orderDao.create(order);
    }

    public List<OrderData> getOrdersByStatus(String status) {
        List<Order> ordersByStatus = orderDao.getOrdersByStatus(status);
        return getOrderDataList(ordersByStatus);
    }

    public List<OrderData> getOrderDataList(List<Order> orders) {
        List<OrderData> orderDataList = new ArrayList<>();

        for (Order order : orders) {
            int bookId = order.getBookId();
            BookData book = bookService.getById(bookId);
            UserData user = userService.getUser(order.getUserId());
            OrderData orderData = new OrderData(order.getId(), user, book, order.getBookSpot(), order.getStatus(), order.getReturnDate());
            Penalty penaltyByOrder = penaltyDao.getPenaltyByOrder(order.getId());
            if (penaltyByOrder != null) {
                PenaltyData penaltyData = new PenaltyData(penaltyByOrder.getPenaltyCost());
                orderData.setPenaltyData(penaltyData);
            }
            orderDataList.add(orderData);

        }
        return orderDataList;
    }
}
