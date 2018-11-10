package com.obsidiam.util.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Just a seeder.
 */
final public class Seeder {

    private SQLiteConnectionFactory sqLiteConnectionFactory = new SQLiteConnectionFactory("org.sqlite.JDBC",
            "jdbc:sqlite:faktury.db");
    private Connection connection = sqLiteConnectionFactory.getConnection();

    private Random generator = new Random();

    private void seedUsers(Statement statement) throws SQLException {
        // seeds 20 sellers and 20 clients
        StringBuilder query = new StringBuilder("insert into User values ");

        ArrayList<String> typ = new ArrayList<String>(){{
            add("Klient");
            add("Sprzedawca");
        }};

        for(int i = 0; i <2; i++){
            for(int j = 0; j < 20; j++){
                query.append("(");
                query.append("null, ");
                query.append("'" + typ.get(i) + j +  "'" + ", ");
                query.append("'Kowalski', ");
                query.append("'Teczowa" + j + "'" + ", ");
                query.append(i);

                if( j != 19)
                    query.append("), ");
                else
                    query.append(");");
            }
        }

        ResultSet rs = statement.executeQuery(query.toString());
    }

    private void seedSummary(Statement statement) throws SQLException {

        StringBuilder query = new StringBuilder("insert into Summary values ");


        for (int i = 0; i < 20; i++){
            query.append("(");
            query.append("null, ");
            query.append(generator.nextInt(19 + 1) + 2 + ", ");
            if (i != 19)
                query.append(generator.nextInt(19 + 1) + 2 + "), ");
            else
                query.append(generator.nextInt(19 + 1) + 2  + ");");
        }

        ResultSet rs = statement.executeQuery(query.toString());
    }

    private void seedArticles(Statement statement) throws SQLException {
        StringBuilder query = new StringBuilder("insert into Article values");

        DecimalFormat df2 = new DecimalFormat(".##");

        for (int i = 0; i < 20; i++){
            query.append("(");
            query.append("null, ");
            query.append("'" + "Article"+ i + "'" + ", ");
            query.append("'" + df2.format(1 + (100 - 1) * generator.nextDouble()) + "'" + ", ");
            query.append(generator.nextInt(50 +1) +1 + ", ");
            if (i != 19)
                query.append(generator.nextInt(20 + 1) + 1 + "), ");
            else
                query.append(generator.nextInt(20 + 1) + 1 + ");");
        }

        ResultSet rs = statement.executeQuery(query.toString());
    }


    public void seed(){
        try {
            Statement statement = connection.createStatement();

            seedUsers(statement);
            seedSummary(statement);
            seedArticles(statement);
        } catch (SQLException se){
            System.err.println(se.getMessage());
        }
    }
}
