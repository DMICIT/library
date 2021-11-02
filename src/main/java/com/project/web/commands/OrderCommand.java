package com.project.web.commands;

import com.project.dao.impl.OrderDaoImpl;
import com.project.entities.Order;
import com.project.entities.User;
import com.project.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
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
        HttpSession session = request.getSession();
        String userEmail = (String)session.getAttribute("user");
        if (userEmail == null) {
            return "redirect:login";
        }

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String bookSpot = request.getParameter("action");
        User userByEmail = UserService.getUserByEmail(userEmail);

        OrderDaoImpl instance = OrderDaoImpl.getOrderDao();
        Date returnDate = null;

        if(bookSpot.equals("abonement")){
            returnDate = Date.valueOf(LocalDate.now().plusMonths(1));
        }else if (bookSpot.equals("library hall")){
            returnDate = Date.valueOf(LocalDate.now().plusDays(1));
        }
        Order order = new Order(userByEmail.getId(), bookId, bookSpot,"expected", returnDate );
        instance.create(order);
        return "redirect:orders";
    }
}
