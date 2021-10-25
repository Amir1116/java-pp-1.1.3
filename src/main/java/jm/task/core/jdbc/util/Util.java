package jm.task.core.jdbc.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private  final static String url = "jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false";
    private  final static String password = "00000000";
    private  final static String userName = "root";
    private  static Connection connection = null;
    public Util(){}

    public static Statement getStatement(){

        Statement statement = null;
        try{
            connection = DriverManager.getConnection(url, userName, password);
            if(connection != null){
                statement = connection.createStatement();
                System.out.println("Connection to Store DB succesfull!");
            }
        } catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
        return statement;
    }

    public static void closeConnection(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e){
                e.getStackTrace();
            }
        }

    }

    public String getUrl() {
        return url;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

}
