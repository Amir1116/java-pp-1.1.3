package main.java.jm.task.core.jdbc.dao;


import main.java.jm.task.core.jdbc.model.User;

import main.java.jm.task.core.jdbc.service.UserService;
import main.java.jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl
        implements UserDao {

    private final SessionFactory sessionFactory = Util.getInstance().createSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("create table if not exists user(id MEDIUMINT primary key auto_increment,name varchar(30), lastName varchar(30), age INT(3))").executeUpdate();
            transaction.commit();
            System.out.println("Table was created!");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception er) {
                    er.printStackTrace();
                }
            }
        }

    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("drop table if exists user").executeUpdate();
            transaction.commit();
            System.out.println("Table was deleted");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception er) {
                    er.printStackTrace();
                }
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User newUser = new User(name, lastName, age);
        Session session = null;
        Transaction transaction = null;
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(String.format("insert into user(name, lastName, age) value ('%s', '%s', %d)",
                    newUser.getName(), newUser.getLastName(), newUser.getAge())).executeUpdate();
            transaction.commit();
            System.out.println(name + " user was added into table!");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception er) {
                    er.printStackTrace();
                }
            }
        }


    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createQuery("delete User where id=" + id).executeUpdate();
            transaction.commit();
            System.out.println("user with id " + id + " was deleted!");
        }  catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception er) {
                    er.printStackTrace();
                }
            }
        }


    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        Transaction transaction = null;
        List<User> userList = null;
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User");
            userList = query.list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception er) {
                    er.printStackTrace();
                }
            }
        }
        System.out.println("got all users!");
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("truncate user").executeUpdate();
            transaction.commit();
            System.out.println("table cleaned!");
        }catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception er) {
                    er.printStackTrace();
                }
            }
        }

    }
}
