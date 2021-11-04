package com.project.services;

import com.project.dao.impl.OrderDaoImpl;
import com.project.entities.Order;
import com.project.entities.User;
import com.project.web.data.UserPrincipal;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


public class OrderService {
    public static void changeStatus(int orderId, String status){

        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        Order order = orderDao.getById(orderId);

        order.setStatus(status);
        orderDao.update(order);
    }

    public static List <Order> getAllOrdersByUser(int userId){
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        return orderDao.getAllOrdersByUser(userId);
    }
}
