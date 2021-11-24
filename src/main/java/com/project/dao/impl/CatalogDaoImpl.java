package com.project.dao.impl;

import com.project.dao.CatalogDao;
import com.project.dao.EntityDao;
import com.project.entities.Catalog;
import com.project.persistance.DataSourceConnectionPoolFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogDaoImpl implements CatalogDao {


    private static final Logger LOG = Logger.getLogger(CatalogDaoImpl.class);
    private static final String SELECT_ALL_CATALOG_QUERY = "SELECT * FROM catalog";
    private static final String SELECT_CATALOG_BY_ID_QUERY = "SELECT * FROM catalog WHERE id = ?";
    private static final String SELECT_CATALOG_BY_BOOK_ID_QUERY = "SELECT * FROM catalog WHERE book_id = ?";
    private static final String INSERT_QUERY = "INSERT INTO catalog (book_id, count ) VALUES (?,?)";
    private static final String UPDATE_CATALOG_QUERY = "UPDATE catalog SET book_id = ?, count = ? WHERE id = ?";
    private static final String ID = "id";
    private static final String BOOK_ID = "book_id";
    private static final String COUNT = "count";

    private static CatalogDaoImpl instance;

    private CatalogDaoImpl() {
    }

    public static synchronized CatalogDaoImpl getInstance() {
        if (instance == null) {
            instance = new CatalogDaoImpl();
        }
        return instance;
    }


    @Override
    public List<Catalog> getAll() {
        List<Catalog> result = new ArrayList<>();
        try (Connection connection = DataSourceConnectionPoolFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATALOG_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery();) {
            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                int bookId = resultSet.getInt(BOOK_ID);
                int count = resultSet.getInt(COUNT);

                Catalog catalog = new Catalog(id, bookId, count);
                result.add(catalog);

            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Catalog getById(int inputId) {

        Catalog result = null;

        try (Connection connection = DataSourceConnectionPoolFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATALOG_BY_ID_QUERY);) {
            preparedStatement.setInt(1, inputId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                int bookId = resultSet.getInt(BOOK_ID);
                int count = resultSet.getInt(COUNT);

                result = new Catalog(id, bookId, count);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public int create(Catalog entity) {
        int result = 0;
        try (Connection connection = DataSourceConnectionPoolFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
        ) {
            preparedStatement.setInt(1, entity.getBookId());
            preparedStatement.setInt(2, entity.getCount());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public int update(Catalog entity) {
        int result = 0;
        try (Connection connection = DataSourceConnectionPoolFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATALOG_QUERY);
        ) {
            preparedStatement.setInt(1, entity.getBookId());
            preparedStatement.setInt(2, entity.getCount());
            preparedStatement.setInt(3, entity.getId());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Catalog getCatalogByBookId(int inputBookId) {

        Catalog result = null;

        try (Connection connection = DataSourceConnectionPoolFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATALOG_BY_BOOK_ID_QUERY);) {
            preparedStatement.setInt(1, inputBookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                int bookId = resultSet.getInt(BOOK_ID);
                int count = resultSet.getInt(COUNT);

                result = new Catalog(id, bookId, count);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

}

