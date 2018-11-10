package com.obsidiam.managers;

import com.obsidiam.util.database.SQLiteConnectionFactory;
import com.obsidiam.util.model.User;
import com.obsidiam.util.model.UserType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class managing users.
 */
public class UserManager {

    private SQLiteConnectionFactory sqLiteConnectionFactory = new SQLiteConnectionFactory("org.sqlite.JDBC",
            "jdbc:sqlite:faktury.db");
    private Connection connection = sqLiteConnectionFactory.getConnection();

    /**
     * Gets user object of given id.
     * @param id id of a user you want to get
     * @return User's object
     * @see User
     */
    public User getUser(int id){
        User user = null;
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from User where user_id like "+ id);

            while(rs.next()) {
                UserType type;

                if (rs.getBoolean("userType"))
                    type = UserType.SELLER;
                else
                    type = UserType.CUSTOMER;

                user = new User(type,
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("surName"),
                        rs.getString("Address"));
            }
        }catch(SQLException se){
            System.err.println(se.getMessage());
        }

        return user;
    }

    /**
     * Creates user's object and stores it in database using passed parameters.
     * @param type user's type
     * @param name name of a user
     * @param surName surname of a user
     * @param address address of a user
     * @return row count edited or updated, -1 if SQL error.
     * @see UserType
     */
    public int createUser(UserType type, String name, String surName, String address){

        Statement statement = null;
        try {
            statement = connection.createStatement();
            return statement.executeUpdate("insert into User(name, surName,Address,userType) values('"+name+"','"+surName+"','"+address+"',"+type.equals(UserType.SELLER)+")");

        } catch (SQLException e) {
            e.printStackTrace();//quiet print.
            return -1;
        }
    }

    /**
     * Gets all users objects stored in the database.
     * @return returns ArrayList of Users
     * @see ArrayList
     * @see User
     */
    public List<User> getAllUsers() {
        Statement statement = null;
        ArrayList<User> users = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("select * from User");
            while (set.next()) {
                users.add(this.getUser(set.getInt("user_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();//quiet print, it wont print.

        }
        return users;
    }
}
