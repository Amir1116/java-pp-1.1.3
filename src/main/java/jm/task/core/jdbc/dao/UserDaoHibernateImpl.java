package main.java.jm.task.core.jdbc.dao;


import main.java.jm.task.core.jdbc.model.User;

import main.java.jm.task.core.jdbc.service.UserService;
import main.java.jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.*;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = HibernateUtil.getInstance().createSessionFactory();
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("create table if not exists user(id MEDIUMINT primary key auto_increment,name varchar(30), lastName varchar(30), age INT(3))").executeUpdate();
        transaction.commit();
        session.close();
        System.out.println("Table was created!");

    }

    @Override
    public void dropUsersTable() {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("drop table if exists user").executeUpdate();
        System.out.println("Table was deleted");
        transaction.commit();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        User newUser = new User(name,lastName,age);
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery(String.format("insert into user(name, lastName, age) value ('%s', '%s', %d)",
                newUser.getName(), newUser.getLastName(), newUser.getAge())).executeUpdate();
        transaction.commit();
        session.close();
        System.out.println(name + " user was added into table!");

    }

    @Override
    public void removeUserById(long id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete User where id=" + id).executeUpdate();
        transaction.commit();
        session.close();
        System.out.println("user with id " + id + " was deleted!");

    }

    @Override
    public List<User> getAllUsers() {
            Session session = this.sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from User");
            List<User> userList =  query.list();
            transaction.commit();
            session.close();
            System.out.println("got all users!");
            return userList;
    }

    @Override
    public void cleanUsersTable() {
            Session session = this.sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("truncate user").executeUpdate();
            transaction.commit();
            session.close();
            System.out.println("table cleaned!");

    }
}
