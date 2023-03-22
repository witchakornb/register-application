package com.reg.register;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectDB {
    public static final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/reg-system";
    public static final String username = "root";
    public static final String password = "123456789";
    static Connection connection = null;
    Statement statement = null;
    public static void connect(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, username, password);
            System.out.println("Connected database successfully...");
            System.out.println("Creating table in given database...");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
