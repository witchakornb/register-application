package com.reg.register;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    public static final String username = "root";
    public static final String password = "123456789";
    public static final String url = "jdbc:mariadb://127.0.0.1:3306/reg-system";


    public static Connection connect() throws SQLException{
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
    public static void test(){
        try {
            Connection conn = connect();
            System.out.println("Connection successful!");
        }catch (Exception e){
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}
