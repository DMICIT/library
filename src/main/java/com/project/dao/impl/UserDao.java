package com.project.dao.impl;

import com.project.dao.EntityDao;
import com.project.entities.Order;
import com.project.entities.User;
import com.project.persistance.DataSourceFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements EntityDao<User> {

    private static final Logger LOG = Logger.getLogger(PenaltyDao.class);
    public static final String SELECT_ALL_QUERY = "SELECT * FROM users";
    public static final String SELECT_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String SEX = "sex";
    public static final String TELEPHONE = "telephone";
    public static final String ROLE = "role";
    public static final String BAN_LIST = "ban_list";

    @Override
    public List<User> getAll() {

        List<User> result = new ArrayList<>();

        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery();) {

            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                String name = resultSet.getString(NAME);
                String email = resultSet.getString(EMAIL);
                String sex = resultSet.getString(SEX);
                String phone = resultSet.getString(TELEPHONE);
                String role = resultSet.getString(ROLE);
                int banList = resultSet.getInt(BAN_LIST);

                User user = new User(id, name, email, sex, phone, role, banList);
                result.add(user);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public User getById(int inputId) {

        User result = null;
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);) {
            preparedStatement.setInt(1, inputId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                String name = resultSet.getString(NAME);
                String email = resultSet.getString(EMAIL);
                String sex = resultSet.getString(SEX);
                String phone = resultSet.getString(TELEPHONE);
                String role = resultSet.getString(ROLE);
                int banList = resultSet.getInt(BAN_LIST);

                result = new User(id, name, email, sex, phone, role, banList);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}