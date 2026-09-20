package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/*
* Database Connection
* */
public class DBConnection {

    public static Connection getConnection() {
        Connection conn = null;

        try{
            conn = DriverManager.getConnection(DBConfig.getUrl(),
                    DBConfig.getUsername(), DBConfig.getPassword()
            );

        }catch(SQLException e) {
            e.printStackTrace();

        }

        return conn;

    }

}
