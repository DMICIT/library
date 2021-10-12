package com.project.dao.impl;

import com.project.dao.EntityDao;
import com.project.entities.Penalty;
import com.project.persistance.DataSourceFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PenaltyDao implements EntityDao<Penalty> {
    private static final Logger LOG = Logger.getLogger(PenaltyDao.class);
    public static final String SELECT_ALL_PENALTIES_QUERY = "SELECT * FROM penalties";
    public static final String SELECT_PENALTIES_BY_ID_QUERY = "SELECT * FROM penalties WHERE id = ?";
    public static final String INSERT_QUERY = "INSERT INTO penalties ( user_id, order_id, penalty_cost ) values(?,?,?)";
    public static final String UPDATE_QUERY = "UPDATE penalties SET( user_id, order_id, penalty_cost ) values(?,?,?)";
    public static final String ID = "id";
    public static final String ORDER_ID = "order_id";
    public static final String USER_ID = "user_id";
    public static final String PENALTY_COST = "penalty_cost";

    @Override
    public List<Penalty> getAll() {

        List<Penalty> result = new ArrayList<>();
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PENALTIES_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery();) {
            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                int orderId = resultSet.getInt(ORDER_ID);
                int userId = resultSet.getInt(USER_ID);
                int penaltyCost = resultSet.getInt(PENALTY_COST);

                Penalty penalty = new Penalty(id, orderId, userId, penaltyCost);
                result.add(penalty);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Penalty getById(int inputId) {

        Penalty result = null;
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PENALTIES_BY_ID_QUERY);) {
            preparedStatement.setInt(1, inputId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                int orderId = resultSet.getInt(ORDER_ID);
                int userId = resultSet.getInt(USER_ID);
                int penaltyCost = resultSet.getInt(PENALTY_COST);

                result = new Penalty(id, orderId, userId, penaltyCost);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public int create(Penalty entity) {
        int result = 0;
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getOrderId());
            preparedStatement.setInt(3, entity.getPenaltyCost());
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public int update(Penalty entity) {
        int result = 0;
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getOrderId());
            preparedStatement.setInt(3, entity.getPenaltyCost());
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}
