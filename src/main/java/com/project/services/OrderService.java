package com.project.services;

import com.project.dao.OrderDao;
import com.project.dao.PenaltyDao;
import com.project.dao.impl.OrderDaoImpl;
import com.project.dao.impl.PenaltyDaoImpl;
import com.project.entities.Book;
import com.project.entities.Order;
import com.project.entities.Penalty;
import com.project.entities.User;
import com.project.web.data.*;
import com.sun.tools.corba.se.idl.constExpr.Or;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


public class OrderService {
    private OrderDao orderDao;
    private BookService bookService;
    private UserService userService;


    public OrderService(OrderDao orderDao, BookService bookService, UserService userService) {
        this.orderDao = orderDao;
        this.bookService = bookService;
        this.userService = userService;
    }

    public OrderService(){
        this(OrderDaoImpl.getInstance(),new BookService(),new UserService());
    }



    public static void changeStatus(int orderId, String status) {

        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        Order order = orderDao.getById(orderId);
        order.setStatus(status);
        orderDao.update(order);
    }

    public List<OrderData> getAllOrdersByUser(int userId) {
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();

        List<Order> orders = orderDao.getAllOrdersByUser(userId);
        return getOrderDataList(orders);
    }

    public void create(Order order) {
        orderDao = OrderDaoImpl.getInstance();
        orderDao.create(order);
    }

    public List<OrderData> getOrdersByStatus(String status) {
        OrderDao orderDao = OrderDaoImpl.getInstance();
        List<Order> ordersByStatus = orderDao.getOrdersByStatus(status);
        return getOrderDataList(ordersByStatus);

    }

    public  List<OrderData> getOrderDataList(List<Order> orders) {

        List<OrderData> orderDataList = new ArrayList<>();
        for (Order order : orders) {
            int bookId = order.getBookId();
            BookData book = bookService.getById(bookId);
            UserData user = userService.getUser(order.getUserId());
            OrderData orderData = new OrderData(order.getId(), user, book, order.getBookSpot(), order.getStatus(), order.getReturnDate());
            PenaltyDao penaltyDao = PenaltyDaoImpl.getInstance();
            Penalty penaltyByOrder = penaltyDao.getPenaltyByOrder(order.getId());
            if (penaltyByOrder != null) {
                PenaltyData penaltyData = new PenaltyData(penaltyByOrder.getPenaltyCost());
                orderData.setPenaltyData(penaltyData);
            }

            orderDataList.add(orderData);
        } return orderDataList;
    }
}
