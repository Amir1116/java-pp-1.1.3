package main.java.jm.task.core.jdbc.dao;


import main.java.jm.task.core.jdbc.model.User;
import main.java.jm.task.core.jdbc.util.Util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl
        implements UserDao {

    public UserDaoJDBCImpl() {
    }


    public void createUsersTable() {

        try (Connection connection = Util.setConnection()) {

            assert connection != null;
            try (
                    PreparedStatement preparedStatement = connection.prepareStatement("create table if not exists user(id int " +
                            "primary key auto_increment,\n" +
                            " name varchar(30),lastName varchar(30), age INT(3))");) {
                preparedStatement.executeUpdate();
                System.out.println("Table was created!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {

        try (Connection connection = Util.setConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement("drop table if exists user")) {
                preparedStatement.executeUpdate();
                System.out.println("Table was deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.setConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement("insert into user values(?,?,?)")) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();

                System.out.println(name + " user was added into database!");
            }
        } catch (
                SQLException e) {
            e.printStackTrace();

        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.setConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement("delete from user where id=?")) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                System.out.println("user deleted!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userlist = new ArrayList<>();

        try (Connection connection = Util.setConnection()) {
            assert connection != null;

            try (PreparedStatement preparedStatement = connection.prepareStatement("select * from user")) {
                try (ResultSet result = preparedStatement.executeQuery();) {
                    System.out.println(result);
                    while (result.next()) {
                        User user = new User(result.getInt("id"), result.getString("name"), result.getString("lastName"),
                                (byte) result.getInt("age"));
                        userlist.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userlist;
    }

    public void cleanUsersTable() {

        try (Connection connection = Util.setConnection()) {
            assert connection != null;

            try (PreparedStatement preparedStatement = connection.prepareStatement("truncate user")) {
                preparedStatement.executeUpdate();
                System.out.println("table was cleaned!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
