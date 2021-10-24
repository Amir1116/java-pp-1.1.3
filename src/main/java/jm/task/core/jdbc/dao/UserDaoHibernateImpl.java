package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    private SessionFactory createFactory(){
        return new Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
    }

    @Override
    public void createUsersTable() {

        Session session = createFactory().getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery("create table if not exists users(id MEDIUMINT primary key auto_increment," +
                " name varchar(30), lastName varchar(30), age INT(3))").executeUpdate();
        session.getTransaction();
        System.out.println("Table was created!");
    }

    @Override
    public void dropUsersTable() {
        Session session = createFactory().getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery("drop table if exists users").executeUpdate();
        System.out.println("Table was deleted");
        session.getTransaction();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User newUser = new User(name,lastName,age);
        Session session = createFactory().getCurrentSession();
        session.beginTransaction();
        session.save(newUser);
        session.getTransaction().commit();
        System.out.println(name + " user was added into table!");
    }

    @Override
    public void removeUserById(long id) {
        Session session = createFactory().getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete User where id="+id).executeUpdate();
        session.getTransaction().commit();
        System.out.println("user with id "+ id+ " was deleted!");
    }

    @Override
    public List<User> getAllUsers() {
        Session session = createFactory().getCurrentSession();
        session.beginTransaction();
        List<User> userList = session.createQuery("from User").list();
        session.getTransaction().commit();
        System.out.println("got all users!");
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = createFactory().getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery("truncate users").executeUpdate();
        session.getTransaction();
    }
}
