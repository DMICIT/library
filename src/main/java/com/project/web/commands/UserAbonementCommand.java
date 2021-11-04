package com.project.web.commands;


import com.project.entities.Order;
import com.project.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class UserAbonementCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Order> allOrders = OrderService.getAllOrdersByUser(Integer.parseInt(request.getParameter("userId")));
        request.setAttribute("allOrders",allOrders);
        return "user-abonement.jsp";
    }
}
