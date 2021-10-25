package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoJDBCImpl();
    @Override
    public void createUsersTable() {
        userDao.createUsersTable();
//        exeStatement("create table if not exists users(id MEDIUMINT primary key auto_increment, name varchar(30), " +
//                "lastName varchar(30), age INT(3))");
//        System.out.println("Table was created!");
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
//        exeStatement("drop table if exists users");
//        System.out.println("Table was deleted");
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}
