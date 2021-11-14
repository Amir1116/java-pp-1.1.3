package main.java.jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private  final static String url = "jdbc:mysql://localhost:3306/user1?autoReconnect=true&useSSL=false";
    private  final static String password = "00000000";
    private  final static String userName = "root";
    private  static Connection connection = null;
    public Util(){}

    public static Connection setConnection(){
        try{
            System.out.println("Connection to Store DB succesfull!");
            return connection = DriverManager.getConnection(url, userName, password);
        } catch(Exception e){
            System.out.println("Connection failed...");
            e.printStackTrace();
        }
        return null;
    }

    public static String getUrl() {
        return url;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUserName() {
        return userName;
    }

    public static Connection getConnection(){
        return connection;
    }

}
