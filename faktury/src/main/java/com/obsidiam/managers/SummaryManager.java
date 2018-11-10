package com.obsidiam.managers;

import com.obsidiam.util.database.SQLiteConnectionFactory;
import com.obsidiam.util.model.Summary;
import com.obsidiam.util.model.SummaryElement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SummaryManager {
    /* manages all the summaries*/

    private SQLiteConnectionFactory sqLiteConnectionFactory = new SQLiteConnectionFactory("org.sqlite.JDBC",
            "jdbc:sqlite:faktury.db");
    private Connection connection = sqLiteConnectionFactory.getConnection();
    private UserManager userManager = new UserManager();

    public Summary getSummary(int id){
        Summary summary = new Summary();
        // get data from the database and prepare the model

        try{
            Statement statement = connection.createStatement();
            ResultSet summarySet = statement.executeQuery("select * from Summary where summary_id like "+id);

            while (summarySet.next()){
                summary.setSeller(userManager.getUser(summarySet.getInt("seller_id")));
                summary.setCustomer(userManager.getUser(summarySet.getInt("client_id")));
            }

            createSummaryElements(id, summary);
        }catch (SQLException se){
            System.err.println(se.getMessage());
        }
        return summary;
    }

    public int createSummary(int client_id, int seller_id){
        Statement statement = null;
        try {
            statement = connection.createStatement();
            return statement.executeUpdate("insert into Summary values (null, "+client_id + ", " + seller_id + ")");

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int createSummaryElement(int summaryId, String articleName, double articlePrice, int articleAmount){
        Statement statement = null;
        try {
            statement = connection.createStatement();
            return statement.executeUpdate("insert into Article values (null, '"+articleName+"', '"+articlePrice+"', "+articleAmount + ", " + summaryId+")");

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private void createSummaryElements(int id, Summary summary){
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet elementSet = statement.executeQuery("select * from Article where summary_id like "+id);

            while(elementSet.next()){
                SummaryElement summaryElement = new SummaryElement();

                summaryElement.setElement(elementSet.getString("article_name"));
                summaryElement.setPrice(elementSet.getFloat("article_price"));
                summaryElement.setAmount(elementSet.getInt("article_amount"));

                summary.addElement(summaryElement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
