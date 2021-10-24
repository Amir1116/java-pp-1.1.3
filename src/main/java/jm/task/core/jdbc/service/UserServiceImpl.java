package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
//       " CREATE TABLE `users`.`new_table` (
//  `id` INT NOT NULL AUTO_INCREMENT,
//  `name` VARCHAR(50) NOT NULL,
//  `lastname` VARCHAR(50) NOT NULL,
//  `age` INT(3) NOT NULL,
//        PRIMARY KEY (`id`));"

    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
