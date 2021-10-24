package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public void createUsersTable() {
        exeStatement("create table if not exists users(id MEDIUMINT primary key auto_increment, name varchar(30), " +
                "lastName varchar(30), age INT(3))");
        System.out.println("Table was created!");
    }

    public void dropUsersTable() {
        exeStatement("drop table if exists users");
        System.out.println("Table was deleted");
    }

    public void saveUser(String name, String lastName, byte age) {
        exeStatement(String.format("insert into users(name, lastName, age) value('%s', '%s', %d)", name, lastName, age));
        System.out.println(name + " user was added into database!");
    }

    public void removeUserById(long id) {
        exeStatement(String.format("delete from users where id=%d", id));
    }

    public List<User> getAllUsers() {
        List<User> userlist  = new ArrayList<>();
        try(Connection connection = Util.setConnection();){
            if(connection != null){
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("select * from users");
                while(result.next()){
                    User user = new User(result.getString("name"),result.getString("lastName"),
                            (byte)result.getInt(
                            "age"));
                    user.setId((long)result.getInt("id"));
                    userlist.add(user);
                }
                statement.close();
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return userlist;
    }

    public void cleanUsersTable() {
        exeStatement("truncate users");
        System.out.println("table was cleaned!");
    }

    private static void exeStatement(String command) {
        try(Connection connection = Util.setConnection();){
            if(connection != null){
                Statement statement = connection.createStatement();
                statement.executeUpdate(command);
                statement.close();
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
