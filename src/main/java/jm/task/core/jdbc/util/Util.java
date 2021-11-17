package main.java.jm.task.core.jdbc.util;

import main.java.jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;
    private static Util util = new Util();

    private Util() {
    }

    public static Util getInstance() {
        if (util == null) {
            util = new Util();
        }
        return util;
    }


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

    public static Session getSession() {
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
        return sessionFactory.getCurrentSession();
    }

    public SessionFactory createSessionFactory() {
        if  ( sessionFactory == null ){
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();
                properties.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
                properties.put(Environment.URL,"jdbc:mysql://localhost:3306/user1?autoReconnect=true&useSSL=false");
                properties.put(Environment.USER, "root");
                properties.put(Environment.PASS,"00000000");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");
                properties.put(Environment.SHOW_SQL,"true");
                properties.put(Environment.HBM2DDL_AUTO,"create-drop");
                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                sessionFactory = configuration.buildSessionFactory();
                System.out.println(sessionFactory);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
