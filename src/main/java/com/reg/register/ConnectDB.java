package com.reg.register;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectDB {
    private final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/reg-system";
    private final String username = "root";
    private final String password = "123456789";
    private Connection connection;
    private Statement statement;
    private List<User> dataUser = new ArrayList<>();

    public ConnectDB() {
        this.connection = null;
        this.statement = null;
        connect();
    }

    private void connect(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Connecting to a database...");
            connection = DriverManager.getConnection(DB_URL, username, password);
            statement = connection.createStatement();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<User> getdata(){
        ObservableList<User> list = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM user";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                list.add(new User(rs.getString("student-id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("gender"), rs.getString("address"), rs.getString("phone"), rs.getString("department"), rs.getString("role")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
