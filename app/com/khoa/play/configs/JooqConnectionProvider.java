package com.khoa.play.configs;

import org.jooq.ConnectionProvider;
import org.jooq.exception.DataAccessException;
import play.Logger;
import play.db.Database;


import java.sql.Connection;
import java.sql.SQLException;

public class JooqConnectionProvider implements ConnectionProvider {

    private Connection connection = null;
    private Database db;

    public JooqConnectionProvider(Database db) {
        this.db = db;
    }

    @Override
    public Connection acquire() throws DataAccessException {
        if (connection == null) {
            connection = db.getConnection();
        }
        return connection;
    }

    @Override
    public void release(Connection released) throws DataAccessException {
        if (this.connection != released) {
            throw new IllegalArgumentException("Expected " + this.connection + " but got " + released);
        }
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            Logger.error("Error closing connection " + connection, e);
        }
    }
}