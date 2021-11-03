package com.project.services;

import com.project.dao.impl.OrderDaoImpl;
import com.project.entities.Order;


public class OrderService {
    public static void changeStatus(int orderId, String status){

        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        Order order = orderDao.getById(orderId);

//        if (status.equals("expected")){
//            order.setStatus("checked out");
//        } else if (status.equals("checked out")){
//            order.setStatus("returned");
//        }
        order.setStatus(status);
        orderDao.update(order);
    }
}
