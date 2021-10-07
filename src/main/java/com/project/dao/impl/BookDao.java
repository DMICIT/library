package com.project.dao.impl;

import com.project.dao.EntityDao;
import com.project.entities.Book;
import com.project.persistance.DataSourceFactory;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements EntityDao<Book> {

    private static final Logger LOG = Logger.getLogger(BookDao.class);
    public static final String SELECT_ALL_QUERY = "SELECT * FROM books";
    public static final String SELECT_BY_ID_QUERY = "SELECT * FROM books WHERE id = ?";
    public static final String ID = "id";
    public static final String AUTHOR = "author";
    public static final String BOOK_NAME = "book_name";
    public static final String BOOK_EDITION = "book_edition";
    public static final String RELIASE_DATE = "reliase_date";

    @Override
    public List<Book> getAll() {
        List<Book> result = new ArrayList<>();
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery();) {

            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                String author = resultSet.getString(AUTHOR);
                String bookName = resultSet.getString(BOOK_NAME);
                String bookEdition = resultSet.getString(BOOK_EDITION);
                Date reliaseDate = resultSet.getDate(RELIASE_DATE);

                Book bookInfo = new Book(id, author, bookName, bookEdition, reliaseDate);
                result.add(bookInfo);

            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Book getById(int inputId) {
        Book result = null;
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);) {
            preparedStatement.setInt(1, inputId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                String author = resultSet.getString(AUTHOR);
                String bookName = resultSet.getString(BOOK_NAME);
                String bookEdition = resultSet.getString(BOOK_EDITION);
                Date reliaseDate = resultSet.getDate(RELIASE_DATE);

                result = new Book(id, author, bookName, bookEdition, reliaseDate);

            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}

