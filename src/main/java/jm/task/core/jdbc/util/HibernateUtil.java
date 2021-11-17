//package main.java.jm.task.core.jdbc.util;
//
//import main.java.jm.task.core.jdbc.model.User;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.cfg.Environment;
//
//import javax.security.auth.login.AppConfigurationEntry;
//import java.util.Properties;
//
//public class HibernateUtil {
//    private static SessionFactory sessionFactory;
//    private static HibernateUtil hibernateUtil = new HibernateUtil();
//
//    private HibernateUtil() {
//    }
//
//    public static HibernateUtil getInstance() {
//        if (hibernateUtil == null) {
//            hibernateUtil = new HibernateUtil();
//        }
//        return hibernateUtil;
//    }
//
//    public SessionFactory createSessionFactory() {
//        if  ( sessionFactory == null ){
//            try {
//                Configuration configuration = new Configuration();
//                Properties properties = new Properties();
//                properties.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
//                properties.put(Environment.URL,"jdbc:mysql://localhost:3306/user1?autoReconnect=true&useSSL=false");
//                properties.put(Environment.USER, "root");
//                properties.put(Environment.PASS,"00000000");
//                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
//                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");
//                properties.put(Environment.SHOW_SQL,"true");
//                properties.put(Environment.HBM2DDL_AUTO,"create-drop");
//                configuration.setProperties(properties);
//                configuration.addAnnotatedClass(User.class);
//
//                sessionFactory = configuration.buildSessionFactory();
//                System.out.println(sessionFactory);
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        return sessionFactory;
//    }
//}