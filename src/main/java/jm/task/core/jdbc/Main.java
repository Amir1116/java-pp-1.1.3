package jm.task.core.jdbc;

//import com.mysql.jdbc.FabricMySQLDriver;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
       Connection con = Util.setConnection();

    }
}

