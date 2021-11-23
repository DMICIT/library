package com.project.dao.impl;

import com.project.dao.UserDao;
import com.project.entities.User;
import com.project.persistance.DataSourceConnectionPoolFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger LOG = Logger.getLogger(PenaltyDaoImpl.class);
    public static final String SELECT_ALL_QUERY = "SELECT * FROM users";
    public static final String SELECT_ALL_BY_ROLE_QUERY = "SELECT * FROM users Where role = ?";
    public static final String SELECT_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    public static final String SELECT_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email = ?";
    public static final String UPDATE_USERS_QUERY = "UPDATE  users SET name = ? , email = ?, sex = ?, telephone = ?, role = ?, ban_list = ?, password = ? WHERE id = ?";
    public static final String CREATE_USERS_QUERY = "INSERT INTO users ( name , email, sex, telephone, role, ban_list, password) values(?,?,?,?,?,?,?)";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String SEX = "sex";
    public static final String TELEPHONE = "telephone";
    public static final String ROLE = "role";
    public static final String BAN_LIST = "ban_list";
    public static final String PASSWORD = "password";

    private static UserDaoImpl instance;
    private UserDaoImpl() {
    }

    public static synchronized UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public List<User> getAll() {

        List<User> result = new ArrayList<>();

        try (Connection connection = DataSourceConnectionPoolFactory.getConnection();
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
                String password = resultSet.getString(PASSWORD);

                User user = new User(id, name, email, sex, phone, role, banList, password);
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
        try (Connection connection = DataSourceConnectionPoolFactory.getConnection();
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
                String password = resultSet.getString(PASSWORD);


                result = new User(id, name, email, sex, phone, role, banList, password);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public int create(User entity) {
        int result = 0;
        try (Connection connection = DataSourceConnectionPoolFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USERS_QUERY)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getSex());
            preparedStatement.setString(4, entity.getPhone());
            preparedStatement.setString(5, entity.getRole());
            preparedStatement.setInt(6, entity.getBanList());
            preparedStatement.setString(7, entity.getPassword());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public int update(User entity) {
        int result = 0;
        try (Connection connection = DataSourceConnectionPoolFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_QUERY)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getSex());
            preparedStatement.setString(4, entity.getPhone());
            preparedStatement.setString(5, entity.getRole());
            preparedStatement.setInt(6, entity.getBanList());
            preparedStatement.setString(7, entity.getPassword());
            preparedStatement.setInt(8, entity.getId());

            result = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public User getByEmail(String inputEmail) {

        User result = null;
        try (Connection connection = DataSourceConnectionPoolFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL_QUERY);) {
            preparedStatement.setString(1, inputEmail);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                String name = resultSet.getString(NAME);
                String email = resultSet.getString(EMAIL);
                String sex = resultSet.getString(SEX);
                String phone = resultSet.getString(TELEPHONE);
                String role = resultSet.getString(ROLE);
                int banList = resultSet.getInt(BAN_LIST);
                String password = resultSet.getString(PASSWORD);

                result = new User(id, name, email, sex, phone, role, banList, password);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<User> getUsersByRole(String inputRole) {
        List<User> result = new ArrayList<>();

        try (Connection connection = DataSourceConnectionPoolFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_ROLE_QUERY);
        ) {
            preparedStatement.setString(1, inputRole);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                String name = resultSet.getString(NAME);
                String email = resultSet.getString(EMAIL);
                String sex = resultSet.getString(SEX);
                String phone = resultSet.getString(TELEPHONE);
                String role = resultSet.getString(ROLE);
                int banList = resultSet.getInt(BAN_LIST);
                String password = resultSet.getString(PASSWORD);

                User user = new User(id, name, email, sex, phone, role, banList, password);
                result.add(user);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public int deleteUser(int userId) {
        int result = 0;
        try (Connection connection = DataSourceConnectionPoolFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setInt(1, userId);
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}
