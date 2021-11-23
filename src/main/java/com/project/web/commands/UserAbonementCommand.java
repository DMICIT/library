package com.project.web.commands;


import com.project.entities.Order;
import com.project.services.BookService;
import com.project.services.OrderService;
import com.project.services.UserService;
import com.project.web.data.BookData;
import com.project.web.data.OrderData;
import com.project.web.data.UserData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


public class UserAbonementCommand implements Command{
   private OrderService orderService;

   public UserAbonementCommand(){
       this(new OrderService());
   }

    public UserAbonementCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<OrderData> allOrders = orderService.getAllOrdersByUser(Integer.parseInt(request.getParameter("userId")));

        request.setAttribute("allOrders",allOrders);
        return "user-abonement.jsp";
    }
}
