package jm.task.core.jdbc.util;

import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionCreator;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection setConnection(){
        String url = "jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false";
        String password = "00000000";
        String userName = "root";
        try{
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection to Store DB succesfull!");
            return connection;
        } catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return null;
        }
    }
}
