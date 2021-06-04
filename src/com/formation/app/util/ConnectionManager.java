package com.formation.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String URL = "jdbc:mysql://localhost:3306/paradise";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connection connection = null;

    private ConnectionManager() {
        //Avoid instantiate
    }

    //SINGLETON INSTENCE
    public static Connection getConnection(){
        //Creating Connection to Database
        if (connection == null) {
            try {
                loadDriver();
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                connection.setAutoCommit(false);
            } catch (SQLException throwables) {
                throw new RuntimeException("Cannot create connection");
            }
        }
        return connection;
    }

    public static void loadDriver(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            System.err.println("Driver MySQL introvable");
        }
    }

    public static void closeConnection() throws SQLException {
        try {
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
