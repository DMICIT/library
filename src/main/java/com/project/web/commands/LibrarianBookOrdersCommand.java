package com.project.web.commands;

import com.project.services.OrderService;
import com.project.services.PenaltyService;
import com.project.web.data.OrderData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LibrarianBookOrdersCommand extends AbstractCommand {
    private PenaltyService penaltyService;
    private OrderService orderService;

    public LibrarianBookOrdersCommand(PenaltyService penaltyService, OrderService orderService) {
        this.penaltyService = penaltyService;
        this.orderService = orderService;
    }

    public LibrarianBookOrdersCommand() {
        this(new PenaltyService(), new OrderService());
    }

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {

        penaltyService.checkPenaltyByLibrarian();

        List<OrderData> orders = orderService.getOrdersByStatus("expected");
        orders.addAll(orderService.getOrdersByStatus("checked out"));
        request.setAttribute("orders", orders);
        return "librarian-orders.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {

        String action = request.getParameter("action");
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        orderService.changeStatus(orderId, action);

        return "redirect:librarian-orders";
    }
}
