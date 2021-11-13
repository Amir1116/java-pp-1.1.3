package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl u = new UserServiceImpl();
        u.createUsersTable();

        u.saveUser("user1", "userLastName1", (byte) 10);
        u.saveUser("user2", "userLastName2", (byte) 20);
        u.saveUser("user3", "userLastName3", (byte) 30);
        u.saveUser("user4", "userLastName4", (byte) 40);

        List<User> users = u.getAllUsers();
        users.forEach(System.out::println);

        u.cleanUsersTable();

        u.dropUsersTable();
    }
}

