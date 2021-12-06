package com.project.web.commands;

import com.project.entities.Order;
import com.project.entities.User;
import com.project.services.OrderService;
import com.project.services.UserService;
import com.project.web.data.OrderData;
import com.project.web.data.UserPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderCommand extends AbstractCommand {

    private UserService userService;
    private OrderService orderService;

    public OrderCommand() {
        this(new UserService(), new OrderService());
    }

    public OrderCommand(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserPrincipal user = (UserPrincipal) session.getAttribute("user");
        if (user != null) {
            User userByEmail = userService.getUserByEmail(user.getEmail());
            int usersIdByEmail = userByEmail.getId();

            List<OrderData> allOrdersByUser = orderService.getAllOrdersByUser(usersIdByEmail);
            List<OrderData> ordersByStatus = new ArrayList<>();
            for (OrderData orderByUser :
                    allOrdersByUser) {
                if (!orderByUser.getStatus().equals("returned")) {
                    ordersByStatus.add(orderByUser);
                }
            }
            request.setAttribute("allOrders", ordersByStatus);
        }
        return "orders.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserPrincipal userEmail = (UserPrincipal) session.getAttribute("user");
        if (userEmail == null) {
            return "redirect:login";
        }

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String bookSpot = request.getParameter("action");
        User userByEmail = userService.getUserByEmail(userEmail.getEmail());

        Date returnDate = null;
        if (bookSpot.equals("abonement")) {
            returnDate = Date.valueOf(LocalDate.now().plusMonths(1));
        } else if (bookSpot.equals("library hall")) {
            returnDate = Date.valueOf(LocalDate.now().plusDays(1));
        }

        Order order = new Order(userByEmail.getId(), bookId, bookSpot, "expected", returnDate);

        orderService.create(order);
        return "redirect:orders";
    }
}
