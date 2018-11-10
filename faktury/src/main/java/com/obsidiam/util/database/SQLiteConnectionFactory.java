package com.obsidiam.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Responsible for connecting to the concrete database using proper driver class.
 */
final public class SQLiteConnectionFactory extends AbstractDatabaseConnectionFactory {
    private String className;
    private Connection connection;

    public SQLiteConnectionFactory(String className, String connectionString){
        super(className,connectionString);
        this.className = className;

        try {
            Class.forName(className);
            this.connection = DriverManager.getConnection(connectionString);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public String getClassName() {
        return this.className;
    }
}
