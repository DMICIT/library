package com.project.dao.impl;

import com.project.dao.EntityDao;
import com.project.dao.PaginationDao;
import com.project.entities.Book;
import com.project.persistance.DataSourceConnectionPoolFactory;
import com.project.web.data.PaginationData;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements EntityDao<Book>, PaginationDao<Book> {

    private static final Logger LOG = Logger.getLogger(BookDao.class);
    public static final String SELECT_ALL_QUERY = "SELECT * FROM books";
    public static final String SELECT_PAGINATION_QUERY = "SELECT SQL_CALC_FOUND_ROWS * FROM books LIMIT ?, ?";
    public static final String SELECT_BY_ID_QUERY = "SELECT * FROM books WHERE id = ?";
    public static final String INSERT_NEW_BOOK_QUERY = "INSERT into books(author, book_name, book_edition, reliase_date) VALUES (?,?,?,?)";
    public static final String UPDATE_BOOKS_QUERY = "UPDATE books SET author = ?, book_name = ?, book_edition = ?, reliase_date = ? WHERE id = ?";
    public static final String DELETE_FROM_BOOKS_WHERE_ID = "DELETE FROM books where id=?";
    public static final String ID = "id";
    public static final String AUTHOR = "author";
    public static final String BOOK_NAME = "book_name";
    public static final String BOOK_EDITION = "book_edition";
    public static final String RELIASE_DATE = "reliase_date";

    private static BookDao instance;

    private BookDao() {
    }

    public static synchronized BookDao getInstance() {
        if (instance == null) {
            instance = new BookDao();
        }
        return instance;
    }

    @Override
    public List<Book> getAll() {
        List<Book> result = new ArrayList<>();
        try (Connection connection = DataSourceConnectionPoolFactory.getConnection();
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
        try (Connection connection = DataSourceConnectionPoolFactory.getConnection();
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
        try (Connection connection = DataSourceConnectionPoolFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_BOOK_QUERY, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, entity.getAuthor());
            preparedStatement.setString(2, entity.getBookName());
            preparedStatement.setString(3, entity.getBookEdition());
            preparedStatement.setDate(4, entity.getReliaseDate());

            result = preparedStatement.executeUpdate();
            if (result != 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setId(generatedKeys.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public int update(Book entity) {
        int result = 0;
        try (Connection connection = DataSourceConnectionPoolFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOKS_QUERY);) {
            preparedStatement.setString(1, entity.getAuthor());
            preparedStatement.setString(2, entity.getBookName());
            preparedStatement.setString(3, entity.getBookEdition());
            preparedStatement.setDate(4, entity.getReliaseDate());
            preparedStatement.setInt(5, entity.getId());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public int delete (Book book) {
        int result = 0;
        try (Connection connection = DataSourceConnectionPoolFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_BOOKS_WHERE_ID);) {
            preparedStatement.setInt(1, book.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public PaginationData<Book> getPagination(int startField, int numbersPerPage) {
        PaginationData<Book> paginationData = new PaginationData();
        List<Book> result = new ArrayList<>();
        try (Connection connection = DataSourceConnectionPoolFactory.getConnection();
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAGINATION_QUERY);
            preparedStatement.setInt(1,startField);
            preparedStatement.setInt(2,numbersPerPage);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                String author = resultSet.getString(AUTHOR);
                String bookName = resultSet.getString(BOOK_NAME);
                String bookEdition = resultSet.getString(BOOK_EDITION);
                Date reliaseDate = resultSet.getDate(RELIASE_DATE);
                Book bookInfo = new Book(id, author, bookName, bookEdition, reliaseDate);
                result.add(bookInfo);

            }
            resultSet.close();
            resultSet = preparedStatement.executeQuery("SELECT FOUND_ROWS()");
            if (resultSet.next()){
                paginationData.setTotalAmount(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        paginationData.setEntityList(result);
        return paginationData;
    }
}

