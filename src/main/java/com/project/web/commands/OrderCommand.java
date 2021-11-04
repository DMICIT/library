package com.project.web.commands;

import com.project.dao.PenaltyDao;
import com.project.dao.impl.BookDao;
import com.project.dao.impl.OrderDaoImpl;
import com.project.dao.impl.PenaltyDaoImpl;
import com.project.entities.Book;
import com.project.entities.Order;
import com.project.entities.Penalty;
import com.project.entities.User;
import com.project.services.UserService;
import com.project.web.data.BookData;
import com.project.web.data.OrderData;
import com.project.web.data.PenaltyData;
import com.project.web.data.UserPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderCommand extends AbstractCommand {
    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserPrincipal user = (UserPrincipal) session.getAttribute("user");
        if (user != null) {
            User userByEmail = UserService.getUserByEmail(user.getEmail());
            int usersIdByEmail = userByEmail.getId();

            OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
            List<Order> allOrdersByUser = orderDao.getAllOrdersByUser(usersIdByEmail);
            List<Order> ordersByStatus = new ArrayList<>();
            for (Order orderByUser :
                    allOrdersByUser) {
                if (orderByUser.getStatus().equals("checked out") && orderByUser.getBookSpot().equals("abonement")) {
                    ordersByStatus.add(orderByUser);
                }
            }

            BookDao bookDao = BookDao.getInstance();
            List<OrderData> orderDataList = new ArrayList<>();
            for (Order order: ordersByStatus) {
                int bookId = order.getBookId();
                Book book = bookDao.getById(bookId);
                BookData bookData = new BookData(book.getId(),book.getAuthor(),book.getBookName(),book.getBookEdition(),book.getReliaseDate());
                OrderData orderData = new OrderData(order.getId(), order.getUserId(),bookData,order.getBookSpot(),order.getStatus(),order.getReturnDate());
                PenaltyDao penaltyDao = PenaltyDaoImpl.getInstance();
                Penalty penaltyByOrder = penaltyDao.getPenaltyByOrder(order.getId());
                if(penaltyByOrder != null){
                    PenaltyData penaltyData = new PenaltyData(penaltyByOrder.getPenaltyCost());
                    orderData.setPenaltyData(penaltyData);
                }
                orderDataList.add(orderData);
            }
            request.setAttribute("allOrders", orderDataList);
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
        User userByEmail = UserService.getUserByEmail(userEmail.getEmail());

        Date returnDate = null;
        if (bookSpot.equals("abonement")) {
            returnDate = Date.valueOf(LocalDate.now().plusMonths(1));
        } else if (bookSpot.equals("library hall")) {
            returnDate = Date.valueOf(LocalDate.now().plusDays(1));
        }

        Order order = new Order(userByEmail.getId(), bookId, bookSpot, "expected", returnDate);
        OrderDaoImpl instance = OrderDaoImpl.getInstance();
        instance.create(order);
        return "redirect:orders";
    }
}
