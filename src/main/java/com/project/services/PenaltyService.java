package com.project.services;

import com.project.dao.OrderDao;
import com.project.dao.PenaltyDao;
import com.project.dao.impl.OrderDaoImpl;
import com.project.dao.impl.PenaltyDaoImpl;
import com.project.entities.Order;
import com.project.entities.Penalty;

import java.time.LocalDate;
import java.util.List;

public class PenaltyService {

    public static void checkUserPenalty(int userId) {
        OrderDao orderDao = OrderDaoImpl.getInstance();
        List<Order> allOrdersByUser = orderDao.getAllOrdersByUser(userId);

        checkPenalty(allOrdersByUser);
    }

    public static void checkPenaltyByLibrarian() {
        OrderDao orderDao = OrderDaoImpl.getInstance();
        List<Order> allOrdersByUser = orderDao.getOrdersByStatus("checked out");

        checkPenalty(allOrdersByUser);
    }

    private static void checkPenalty(List<Order> allOrdersByUser) {
        PenaltyDao penaltyDao = PenaltyDaoImpl.getInstance();
        LocalDate now = LocalDate.now();
        for (Order order : allOrdersByUser) {
            Penalty penaltyByOrder = penaltyDao.getPenaltyByOrder(order.getId());
            if (penaltyByOrder != null) {
                LocalDate returnDate = order.getReturnDate().toLocalDate();
                if (order.getStatus().equals("checked out") && returnDate.isBefore(now)) {
                    Penalty penalty = new Penalty(order.getId(), order.getUserId(), 500);
                    penaltyDao.create(penalty);
                }
            }
        }
    }


}
