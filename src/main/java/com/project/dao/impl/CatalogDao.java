package com.project.dao.impl;

import com.project.dao.EntityDao;
import com.project.entities.Catalog;
import com.project.persistance.DataSourceFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogDao implements EntityDao<Catalog> {

    private static final Logger LOG = Logger.getLogger(CatalogDao.class);
    public static final String SELECT_ALL_CATALOG_QUERY = "SELECT * FROM catalog";
    public static final String SELECT_CATALOG_BY_ID_QUERY = "SELECT * FROM catalog WHERE id = ?";
    public static final String INSERT_QUERY = "INSERT INTO catalog (book_id, status ) VALUES (?,?)";
    public static final String UPDATE_CATALOG_QUERY = "UPDATE catalog SET book_id = ?, status = ? WHERE id = ?";
    public static final String ID = "id";
    public static final String BOOK_ID = "book_id";
    public static final String STATUS = "status";

    @Override
    public List<Catalog> getAll() {
        List<Catalog> result = new ArrayList<>();
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATALOG_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery();) {
            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                int bookId = resultSet.getInt(BOOK_ID);
                String status = resultSet.getString(STATUS);

                Catalog catalog = new Catalog(id, bookId, status);
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

        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATALOG_BY_ID_QUERY);) {
            preparedStatement.setInt(1, inputId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                int bookId = resultSet.getInt(BOOK_ID);
                String status = resultSet.getString(STATUS);

                result = new Catalog(id, bookId, status);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public int create(Catalog entity) {
        int result = 0;
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
        ) { preparedStatement.setInt(1,entity.getBookId());
            preparedStatement.setString(2,entity.getStatus());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public int update(Catalog entity) {
        int result = 0;
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATALOG_QUERY );
        ) { preparedStatement.setInt(1,entity.getBookId());
            preparedStatement.setString(2,entity.getStatus());
            preparedStatement.setInt(3,entity.getId());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}

