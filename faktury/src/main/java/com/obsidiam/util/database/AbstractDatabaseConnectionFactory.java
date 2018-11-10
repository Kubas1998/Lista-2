package com.obsidiam.util.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Abstract class for DatabaseConnection factories.
 */
public abstract class AbstractDatabaseConnectionFactory {
    public AbstractDatabaseConnectionFactory(String className, String connectionString){}
    public abstract Connection getConnection();
    public abstract String getClassName();
}
