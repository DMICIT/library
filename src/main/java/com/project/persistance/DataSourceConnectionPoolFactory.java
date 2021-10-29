package com.project.persistance;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.project.entities.Book;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class DataSourceConnectionPoolFactory {
    private static final Logger LOG = Logger.getLogger(DataSourceConnectionPoolFactory.class);
    private static final DataSourceConnectionPoolFactory INSTANCE = new DataSourceConnectionPoolFactory();

    private static DataSource dataSource;

    public DataSourceConnectionPoolFactory() {
    }
    static {
        try {
            Context initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/library");

        } catch ( NamingException e) {
            LOG.error(e.getMessage(), e);
        }
    }
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
    }
        return connection;
}

    public static void main(String[] args) throws SQLException {
        Connection connection = DataSourceConnectionPoolFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM library.books WHERE id = ?");
        preparedStatement.setInt (1 ,2);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            int id = resultSet.getInt(1);
            String author = resultSet.getString(2);
            String bookName = resultSet.getString(3);
            String bookEdition = resultSet.getString(4);
            Date reliaseDate = resultSet.getDate(5);

            Book bookInfo = new Book(id,author,bookName,bookEdition,reliaseDate);
            LOG.info (bookInfo);
            System.out.println(bookInfo);
        }
    }
}
