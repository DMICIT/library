package com.project.web.commands;


import com.project.services.OrderService;
import com.project.services.UserService;
import com.project.web.data.OrderData;
import com.project.web.data.UserData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class UserAbonementCommand implements Command {
    private OrderService orderService;
    private UserService userService;

    public UserAbonementCommand() {
        this(new OrderService(), new UserService());
    }

    public UserAbonementCommand(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int userId = Integer.parseInt(request.getParameter("userId"));
        List<OrderData> allOrders = orderService.getAllOrdersByUser(userId);
        UserData userData = userService.getUser(userId);
        request.setAttribute("allOrders", allOrders);
        request.setAttribute("userData", userData);
        return "user-abonement.jsp";
    }
}
