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
    public static final String INSERT_NEW_BOOK_QUERY = "INSERT into books(author, book_name, book_edition, reliase_date) VALUES (?,?,?,?)";
    public static final String UPDATE_BOOKS_QUERY = "UPDATE books SET author = ?, book_name = ?, book_edition = ?, reliase_date = ? WHERE id = ?";

    private static BookDao instance;
    private BookDao(){};

    public static synchronized BookDao getInstance(){
        if (instance == null){
            instance = new BookDao();
        }
        return instance;
    }

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

    @Override
    public int create(Book entity) {
        int result = 0;
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_BOOK_QUERY);

        ) {
            preparedStatement.setString(1, entity.getAuthor());
            preparedStatement.setString(2, entity.getBookName());
            preparedStatement.setString(3, entity.getBookEdition());
            preparedStatement.setDate(4, entity.getReliaseDate());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public int update(Book entity) {
        int result = 0;
        try (Connection connection = DataSourceFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOKS_QUERY);) {
            preparedStatement.setString(1, entity.getAuthor());
            preparedStatement.setString(2, entity.getBookName());
            preparedStatement.setString(3, entity.getBookEdition());
            preparedStatement.setDate(4, entity.getReliaseDate());
            preparedStatement.setInt(5,entity.getId());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}

