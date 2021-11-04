package com.project.web.commands;

import com.project.dao.OrderDao;
import com.project.dao.impl.OrderDaoImpl;
import com.project.entities.Order;
import com.project.services.OrderService;
import com.project.services.PenaltyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LibrarianBookOrdersCommand extends AbstractCommand {

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {

        PenaltyService.checkPenaltyByLibrarian();
        OrderDao orderDao = OrderDaoImpl.getInstance();
        List<Order> orders = orderDao.getOrdersByStatus("expected");
        orders.addAll(orderDao.getOrdersByStatus("checked out"));
        request.setAttribute("orders", orders);
        return "librarian-orders.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {

        String action = request.getParameter("action");
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        if (action.equals("checked out")){
            OrderService.changeStatus(orderId,"checked out");
        }else if (action.equals("returned")){
            OrderService.changeStatus(orderId,"returned");
        }
        return "redirect:librarian-orders";
    }
}
