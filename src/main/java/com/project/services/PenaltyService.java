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

    private PenaltyDao penaltyDao;
    private OrderDao orderDao;

    public PenaltyService(){
        this(PenaltyDaoImpl.getInstance());
    }

    public PenaltyService(PenaltyDao penaltyDao) {
        this.penaltyDao = penaltyDao;
    }

    public void checkUserPenalty(int userId) {
        orderDao = OrderDaoImpl.getInstance();
        List<Order> allOrdersByUser = orderDao.getAllOrdersByUser(userId);

        checkPenalty(allOrdersByUser);
    }

    public void checkPenaltyByLibrarian() {
        orderDao = OrderDaoImpl.getInstance();
        List<Order> allOrdersByUser = orderDao.getOrdersByStatus("checked out");

        checkPenalty(allOrdersByUser);
    }

    private void checkPenalty(List<Order> allOrdersByUser) {
        penaltyDao = PenaltyDaoImpl.getInstance();
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
