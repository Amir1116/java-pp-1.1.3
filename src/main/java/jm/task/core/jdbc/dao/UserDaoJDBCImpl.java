package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    private void exeStatement(String command) {
        try{
            Util.setConnection();
            PreparedStatement preparedStatement = Util.getConnection().prepareStatement(command);
            preparedStatement.executeUpdate(command);
        } catch (SQLException e){
            e.printStackTrace();
            Util.closeConnection();
        }
    }

    public void createUsersTable() {
        exeStatement("create table if not exists users(id MEDIUMINT primary key auto_increment,\n" +
                " name varchar(30),lastName varchar(30), age INT(3))");
        System.out.println("Table was created!");
    }

    public void dropUsersTable() {
        exeStatement("drop table if exists users");
        System.out.println("Table was deleted");
    }

    public void saveUser(String name, String lastName, byte age) {
        exeStatement("insert into users(name, lastName, age) value('"+name+"','" + lastName+"',"+age+")");
        System.out.println(name + " user was added into database!");
    }

    public void removeUserById(long id) {
        exeStatement(String.format("delete from users where id=%d", id));
    }

    public List<User> getAllUsers() {
        List<User> userlist  = new ArrayList<>();
        try{
            Util.setConnection();
            PreparedStatement preparedStatement =  Util.getConnection().prepareStatement("select * from users");
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                User user = new User(result.getInt("id"),result.getString("name"),result.getString("lastName"),
                        (byte)result.getInt("age"));
                userlist.add(user);
            }
            try{
                result.close();
                preparedStatement.close();
                Util.closeConnection();
            }catch (SQLException e){
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userlist;
    }

    public void cleanUsersTable() {
        exeStatement("truncate users");
        System.out.println("table was cleaned!");
    }
}
