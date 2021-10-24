package com.project.web.commands;

import com.project.dao.impl.OrderDaoImpl;
import com.project.entities.Order;
import com.project.entities.User;
import com.project.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrderCommand extends AbstractCommand{
    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String user = (String)session.getAttribute("user");
        if (user != null){
            User userByEmail = UserService.getUserByEmail(user);
            int usersIdByEmail = userByEmail.getId();

            OrderDaoImpl orderDao = OrderDaoImpl.getOrderDao();
            List<Order> allOrdersByUser = orderDao.getAllTicketsByUser(usersIdByEmail);
            request.setAttribute("allOrders",allOrdersByUser);
        }

        return "orders.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        HttpSession session = request.getSession();
        String userEmail = (String)session.getAttribute("user");
        User userByEmail = UserService.getUserByEmail(userEmail);

        OrderDaoImpl instance = OrderDaoImpl.getOrderDao();
        Order order = new Order(userByEmail.getId(), bookId, "abonement","expected", null );
        instance.create(order);
        return "orders.jsp";
    }
}
