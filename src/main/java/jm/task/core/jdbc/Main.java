package main.java.jm.task.core.jdbc;

//import com.mysql.jdbc.FabricMySQLDriver;

import main.java.jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import main.java.jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

import java.util.List;


public class Main {
    public static void main(String[] args) {
//        UserServiceImpl u = new UserServiceImpl();
//        u.createUsersTable();
//
//        u.saveUser("user1", "userLastName1", (byte) 10);
//        u.saveUser("user2", "userLastName2", (byte) 20);
//        u.saveUser("user3", "userLastName3", (byte) 30);
//        u.saveUser("user4", "userLastName4", (byte) 40);
//
//        List<User> users = u.getAllUsers();
//        users.forEach(System.out::println);
//
//        u.cleanUsersTable();
//
//        u.dropUsersTable();

        //==============================================versuib2
        UserDaoHibernateImpl userHibernate = new UserDaoHibernateImpl();
        userHibernate.createUsersTable();

        userHibernate.saveUser("user1", "userLastName1", (byte) 10);
        userHibernate.saveUser("user2", "userLastName2", (byte) 20);
        userHibernate.saveUser("user3", "userLastName3", (byte) 30);
        userHibernate.saveUser("user4", "userLastName4", (byte) 40);

        List<User> users = userHibernate.getAllUsers();
        users.forEach(System.out::println);

        userHibernate.cleanUsersTable();

        userHibernate.dropUsersTable();
    }
}

