package com.project.persistance;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.project.entities.Book;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;

public class DataSourceFactory {
    private static final Logger LOG = Logger.getLogger(DataSourceFactory.class);
    private static final DataSourceFactory INSTANCE = new DataSourceFactory();

    private static DataSource dataSource;

    public DataSourceFactory() {
    }
    static {
        try {
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setUrl("jdbc:mysql://localhost:3306/library?useUnicode=true&serverTimezone=UTC&useSSL=false");
            mysqlDataSource.setDatabaseName("library");
            mysqlDataSource.setCharacterEncoding("UTF-8");
            mysqlDataSource.setUser("root");
            mysqlDataSource.setPassword("rootroot");

            dataSource = mysqlDataSource;
        } catch (SQLException e) {
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
        Connection connection = DataSourceFactory.getConnection();
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
