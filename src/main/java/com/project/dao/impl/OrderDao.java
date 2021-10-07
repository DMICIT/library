package com.project.dao.impl;

import com.project.dao.EntityDao;
import com.project.entities.Order;
import com.project.persistance.DataSourceFactory;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements EntityDao<Order> {

    private static final Logger LOG = Logger.getLogger(PenaltyDao.class);
    public static final String SELECT_ALL_ORDERS_QUERY = "SELECT * FROM orders";
    public static final String SELECT_ORDERS_BY_ID_QUERY = "SELECT * FROM orders WHERE id = ?";
    public static final String ID = "id";
    public static final String ID_USER = "id_user";
    public static final String ID_BOOK = "id_book";
    public static final String BOOK_SPOT = "book_spot";
    public static final String STATUS = "status";
    public static final String RETURN_DATE = "return_date";

    @Override
    public List<Order> getAll() {
        List<Order> result = new ArrayList<>();
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery();) {
            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                int userId = resultSet.getInt(ID_USER);
                int bookId = resultSet.getInt(ID_BOOK);
                String bookSpot = resultSet.getString(BOOK_SPOT);
                String status = resultSet.getString(STATUS);
                Date returnDate = resultSet.getDate(RETURN_DATE);

                Order order = new Order(id, userId, bookId, bookSpot, status, returnDate);
                result.add(order);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Order getById(int inputId) {

        Order result = null;
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERS_BY_ID_QUERY);){
             preparedStatement.setInt(1, inputId);
             ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                int userId = resultSet.getInt(ID_USER);
                int bookId = resultSet.getInt(ID_BOOK);
                String bookSpot = resultSet.getString(BOOK_SPOT);
                String status = resultSet.getString(STATUS);
                Date returnDate = resultSet.getDate(RETURN_DATE);

                result = new Order(id, userId, bookId, bookSpot, status, returnDate);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}
