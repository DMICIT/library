package com.project.dao.impl;

import com.project.dao.OrderDao;
import com.project.entities.Order;
import com.project.persistance.DataSourceFactory;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private static final Logger LOG = Logger.getLogger(PenaltyDao.class);
    public static final String SELECT_ALL_ORDERS_QUERY = "SELECT * FROM orders";
    public static final String SELECT_ORDERS_BY_ID_QUERY = "SELECT * FROM orders WHERE id = ?";
    public static final String INSERT_INTO_ORDERS_QUERY = "INSERT INTO orders ( id_book, id_user, book_spot, status, return_date ) values(?,?,?,?,?)";
    public static final String UPDATE_ORDERS_QUERY = "UPDATE orders SET id_book = ?, id_user = ?, book_spot = ?, status= ?, return_date = ? WHERE id =?";
    public static final String SELECT_FROM_ORDERS_BY_USER_ID_QUERY = "SELECT * FROM orders WHERE ID_USER = ?";
    public static final String ID = "id";
    public static final String ID_USER = "id_user";
    public static final String ID_BOOK = "id_book";
    public static final String BOOK_SPOT = "book_spot";
    public static final String STATUS = "status";
    public static final String RETURN_DATE = "return_date";

    private static OrderDaoImpl instance;

    private OrderDaoImpl() {
    }

    public static synchronized OrderDaoImpl getOrderDao() {
        if (instance == null) {
            instance = new OrderDaoImpl();
        }
        return instance;
    }

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
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERS_BY_ID_QUERY);) {
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

    @Override
    public int create(Order entity) {
        int result = 0;
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_ORDERS_QUERY)) {
            preparedStatement.setInt(1, entity.getBookId());
            preparedStatement.setInt(2, entity.getUserId());
            preparedStatement.setString(3, entity.getBookSpot());
            preparedStatement.setString(4, entity.getStatus());
            preparedStatement.setDate(5, entity.getReturnDate());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public int update(Order entity) {
        int result = 0;
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDERS_QUERY)) {
            preparedStatement.setInt(1, entity.getBookId());
            preparedStatement.setInt(2, entity.getUserId());
            preparedStatement.setString(3, entity.getBookSpot());
            preparedStatement.setString(4, entity.getStatus());
            preparedStatement.setDate(5, entity.getReturnDate());
            preparedStatement.setInt(6, entity.getId());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Order> getAllTicketsByUser(int usersId) {
        List<Order> result = new ArrayList<>();
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_ORDERS_BY_USER_ID_QUERY)) {
            preparedStatement.setInt(1, usersId);
            ResultSet resultSet = preparedStatement.executeQuery();

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
}

