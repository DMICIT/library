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

        } catch (NamingException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return connection;
    }
}
