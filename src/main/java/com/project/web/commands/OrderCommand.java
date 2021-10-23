package com.project.web.commands;

import com.project.dao.impl.OrderDao;
import com.project.entities.Order;
import com.project.entities.User;
import com.project.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderCommand extends AbstractCommand{
    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        return "orders.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        HttpSession session = request.getSession();
        String userEmail = (String)session.getAttribute("user");
        User userByEmail = UserService.getUserByEmail(userEmail);

        OrderDao instance = OrderDao.getOrderDao();
        Order order = new Order(userByEmail.getId(), bookId, "abonement","expected", null );
        instance.create(order);
        return "orders.jsp";
    }
}
